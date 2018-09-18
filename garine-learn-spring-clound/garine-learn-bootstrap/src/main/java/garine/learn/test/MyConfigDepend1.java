package garine.learn.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author zhoujy
 * @date 2018年09月14日
 **/
@Component
public class MyConfigDepend1 implements InitializingBean{

    @Autowired
    MyConfig myConfig;

    @PostConstruct
    public void pre(){
        System.out.println("----------------MyConfigDepend1 init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------------MyConfigDepend1 finish,myconfig is"+ myConfig);
        System.out.println("---------------MyConfigDepend1 finish,this is"+ this);
    }
}
