package garine.learn.custom.loadblance.controller;

import garine.learn.custom.loadblance.annotation.LoadblancedRestTemplate;
import garine.learn.custom.loadblance.interceptor.LoadblanceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
public class LoadbalceTestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{applicationName}/hello")
    public String hello(@PathVariable String applicationName){
        return restTemplate.getForObject(applicationName + "/hello", String.class);
    }

    @Bean
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

    @Bean
    public LoadblanceInterceptor loadblanceInterceptor2(){
        //按顺序先初始化l1，上面注入也是l1
        LoadblanceInterceptor l2 =  new LoadblanceInterceptor();
        return l2;
    }
}
