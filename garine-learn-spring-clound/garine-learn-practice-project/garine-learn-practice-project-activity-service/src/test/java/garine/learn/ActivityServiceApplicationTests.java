package garine.learn;

import garine.learn.common.sdk.redis.RedisDistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ActivityServiceApplication.class})
@Slf4j
public class ActivityServiceApplicationTests {

    @Resource
    private RedisDistributeLock redisDistributeLock;
    @Test
    public void testRedislock() throws InterruptedException {
        for(int i=0;i < 50;i++){
            int finalI = i;
            new Thread(() ->{
                    if (redisDistributeLock.tryLock("TEST_LOCK_KEY", "TEST_LOCK_VAL_"+ finalI, 1000* 100, 1000*20)){
                        try {
                            log.warn("get lock successfully with lock value:-----" + "TEST_LOCK_VAL_"+ finalI);
                            Thread.sleep(2000);
                            if (!redisDistributeLock.tryUnLock("TEST_LOCK_KEY", "TEST_LOCK_VAL_"+ finalI)){
                                throw new RuntimeException("release lock fail");
                            }
                            log.warn("release lock successfully with lock value:-----" + "TEST_LOCK_VAL_"+ finalI);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        log.warn("get lock fail with lock value:-----" + "TEST_LOCK_VAL_"+ finalI);
                    }
            }).start();
        }

        Thread.sleep(1000*1000);
    }

}
