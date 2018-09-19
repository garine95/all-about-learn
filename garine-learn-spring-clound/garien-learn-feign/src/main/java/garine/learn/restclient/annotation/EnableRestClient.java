package garine.learn.restclient.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientRegistar.class)
public @interface EnableRestClient {
    /**
     * 扫描client路径
     * @return
     */
    String[] scanbasepackages() default {};

    /**
     * 导入client标注的类，优先级高
     * @return
     */
    Class<?>[] importClass() default {};
}
