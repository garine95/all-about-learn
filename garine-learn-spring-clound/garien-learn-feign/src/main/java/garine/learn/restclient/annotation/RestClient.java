package garine.learn.restclient.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestClient {

    /**
     * 服务名称
     * @return
     */
    String applicationName();

    /**
     * 指定的服务地址
     * @return
     */
    String serviceUrl() default "";
}
