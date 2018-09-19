package garine.learn.breaker.annotation;

import java.lang.annotation.*;

/**
 * 标注需要熔断的服务方法
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BreakerCommand {

    String strategy() default "SEMAPHORE" ;
}
