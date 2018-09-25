package garine.learn.test.auwired.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)//在这里不起作用
@Import(TestAutowiredRegistar.class)
public class TFirstConfig {
    //1.先处理TestAutowiredRegistar的ImportBeanDefinitionRegistrar#registerBeanDefinitions
}
