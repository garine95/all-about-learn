package garine.learn.common.sdk.annotation;

import garine.learn.common.sdk.annotation.support.SuchBeansRegistar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SuchBeansRegistar.class})
public @interface EnableSuchSDKBeans {

    /**
     * 需要创建bean的class
     * @return
     */
    Class[] configClasses();
}
