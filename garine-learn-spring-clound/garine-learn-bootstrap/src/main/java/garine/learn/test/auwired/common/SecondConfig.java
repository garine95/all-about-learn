package garine.learn.test.auwired.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)//在这里不起作用
public class SecondConfig {
    @Bean
    public TestRegistarDependOn testRegistarDependOn(){
        return new TestRegistarDependOn();
    }
}
