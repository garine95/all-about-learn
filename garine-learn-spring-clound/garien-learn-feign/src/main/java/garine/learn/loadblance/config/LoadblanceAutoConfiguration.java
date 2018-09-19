package garine.learn.loadblance.config;

import garine.learn.loadblance.Interceptor.LoadblanceInterceptor;
import garine.learn.loadblance.annotation.LoadblancedRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * 负载均衡自动配置
 */
@Configuration
public class LoadblanceAutoConfiguration {
    @Bean(name = "lancedRestTemplate")
    @LoadblancedRestTemplate
    public RestTemplate restTemplate(){//括号参数是依赖注入
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public Object setAndInitRestTamplates(@LoadblancedRestTemplate Collection<RestTemplate> restTemplates, LoadblanceInterceptor loadblanceInterceptor){
        //@LoadblancedRestTemplate指定只能注入被@LoadblancedRestTemplate修饰的RestTemplate,LoadblanceInterceptor默认依赖注入
        restTemplates.forEach(val -> {
            val.getInterceptors().add(loadblanceInterceptor);
        });
        return new Object();
    }

    @Bean
    public LoadblanceInterceptor loadblanceInterceptor(){
        LoadblanceInterceptor l1 =  new LoadblanceInterceptor();
        return l1;
    }

}
