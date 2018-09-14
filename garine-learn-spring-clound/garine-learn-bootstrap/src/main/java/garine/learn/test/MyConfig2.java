package garine.learn.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author zhoujy
 * @date 2018年09月14日
 **/
@Configuration
public class MyConfig2 {

    @Bean
    public HoldConfigDepend1 holdConfigDepend1(MyConfigDepend1 myConfigDepend1){
        HoldConfigDepend1 holdConfigDepend1 = new HoldConfigDepend1();
        holdConfigDepend1.setMyConfigDepend1(myConfigDepend1);
        return holdConfigDepend1;
    }
}
