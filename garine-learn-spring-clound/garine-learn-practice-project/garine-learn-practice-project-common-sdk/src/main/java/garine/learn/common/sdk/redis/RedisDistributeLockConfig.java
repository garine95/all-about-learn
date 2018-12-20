package garine.learn.common.sdk.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoujy
 * @date 2018年12月19日
 **/
@Configuration
public class RedisDistributeLockConfig {

    @Value("${spring.redis.cluster.nodes}")
    String redisNodes;

    @Bean
    public RedisDistributeLock redisDistributeLock(JedisCluster jedisCluster){
        return new RedisDistributeLock(jedisCluster);
    }

    @Bean
    public JedisCluster jedisCluster(){
        return new JedisCluster(pharseHostAnport());
    }

    private Set<HostAndPort> pharseHostAnport(){
        if (StringUtils.isEmpty(redisNodes)){
            throw new RuntimeException("redis nodes can't be null or empty");
        }
        String[] hps = redisNodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String hp : hps) {
            String[] hap = hp.split(":");
            hostAndPorts.add(new HostAndPort(hap[0], Integer.parseInt(hap[1])));
        }
        return hostAndPorts;
    }
}
