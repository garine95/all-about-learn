package garine.learn.TestProModel.monitor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProcessFor {

    String value();
}
