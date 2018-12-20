package garine.learn.common.sdk.redis;

import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * JedisCluster + lua脚本实现分布式锁
 * @author zhoujy
 * @date 2018年12月19日
 **/
public class RedisDistributeLock {

    private JedisCluster jedisCluster;

    private static final String DISTRIBUTE_LOCK_SCRIPT_UNLOCK_KEY = "distribute_lock_script_unlock_key";

    /**
     * lua脚本：判断锁住值是否为当前线程持有，是的话解锁，不是的话解锁失败
     */
    private static final String DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL = "if" +
            " redis.call('get', KEYS[1]) == ARGV[1]" +
            " then" +
            " return redis.call('del', KEYS[1])" +
            " else" +
            " return 0" +
            " end";

    private volatile String unlockSha1 = "";

    private static final Long UNLOCK_SUCCESS_CODE = 1L;

    private static final String LOCK_SUCCESS_CODE = "ok";

    public RedisDistributeLock(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * 根据loopTryTime循环重试
     * @param lockKey 锁key
     * @param lockVal 锁值，用于解锁校验
     * @param expiryTime 锁过期时间
     * @param loopTryTime 获取失败时，循环重试获取锁的时长
     * @return 是否获得锁
     */
    public boolean tryLock(String lockKey, String lockVal, long expiryTime, Long loopTryTime){
        Long endTime = System.currentTimeMillis() + loopTryTime;
        while (System.currentTimeMillis() < endTime){
            if (tryLock(lockKey, lockKey, expiryTime)){
                return true;
            }
        }
        return false;
    }

    /**
     * 一次尝试，快速失败
     * @param lockKey 锁key
     * @param lockVal 锁值，用于解锁校验
     * @param expiryTime 锁过期时间 MILLS
     * @return 是否获得锁
     */
    public boolean tryLock(String lockKey, String lockVal, long expiryTime){
        //相比一般的分布式锁，这里把setNx和setExpiry操作合并到一起，jedis保证原子性，避免连个命令之间出现当即等问题
        //这里也可以我们使用lua脚本实现
        String result = jedisCluster.set(lockKey, lockVal, "NX", "PX", expiryTime);
        return LOCK_SUCCESS_CODE.equalsIgnoreCase(result);
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁key
     * @param lockVal 锁值
     * @return 是否释放成功
     */
    public boolean tryUnLock(String lockKey, String lockVal){
        //redis支持脚本缓存，返回哈希码，后续可以继续用来调用脚本
        if (StringUtils.isEmpty(unlockSha1) || !jedisCluster.scriptExists(unlockSha1, DISTRIBUTE_LOCK_SCRIPT_UNLOCK_KEY)){
            unlockSha1 = storeScript();
        }
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);
        List<String> argv = new ArrayList<>();
        argv.add(lockVal);
        Object result = jedisCluster.evalsha(unlockSha1, keys, argv);
        return UNLOCK_SUCCESS_CODE.equals(result);
    }

    private String storeScript(){
        return jedisCluster.scriptLoad(DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL, DISTRIBUTE_LOCK_SCRIPT_UNLOCK_KEY);
    }
}
