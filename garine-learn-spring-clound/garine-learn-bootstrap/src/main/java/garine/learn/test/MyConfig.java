package garine.learn.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author zhoujy
 * @date 2018年09月14日
 **/
@Component
public class MyConfig implements InitializingBean {
    @Autowired
    MyConfigDepend1 myConfigDepend1;

    @PostConstruct
    public void pre(){
        System.out.println("----------------myconfig init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------------myconfig finish, myConfigDepend1 is"+ myConfigDepend1);
        System.out.println("---------------myconfig finish,this is"+ this);
    }
}
