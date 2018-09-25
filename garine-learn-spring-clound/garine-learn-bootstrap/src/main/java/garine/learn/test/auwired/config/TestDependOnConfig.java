package garine.learn.test.auwired.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDependOnConfig implements InitializingBean{
    @Autowired
    ZTestConfigurationDependOn zTestConfigurationDependOn;//观察这个依赖什么时候进行初始化，断点getBean调试

    @Bean
    public ConfigInitBean configInitBean(){
        //zTestConfigurationDependOn.saySome();
        return new ConfigInitBean();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        zTestConfigurationDependOn.saySome();
    }
}
