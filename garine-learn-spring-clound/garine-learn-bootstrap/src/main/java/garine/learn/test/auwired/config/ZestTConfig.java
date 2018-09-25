package garine.learn.test.auwired.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZestTConfig {
    @Autowired
    ZLastBean zLastBean;
    @Bean
    public ZTestConfigurationDependOn zTestConfigurationDependOn(){
        zLastBean.sayLast();
        return new ZTestConfigurationDependOn();
    }
}
