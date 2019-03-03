package garine.learn.common.sdk.log.annotation;

import java.lang.annotation.*;

/**
 * 标记方法为日志处理器
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogHandler {
    String name() default "";
}
