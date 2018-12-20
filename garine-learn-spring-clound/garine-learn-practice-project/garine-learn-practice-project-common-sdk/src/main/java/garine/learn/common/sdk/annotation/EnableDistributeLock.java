package garine.learn.common.sdk.annotation;

import garine.learn.common.sdk.redis.RedisDistributeLockConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisDistributeLockConfig.class)
public @interface EnableDistributeLock {
}
