package garine.learn.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author zhoujy
 * @date 2018年09月14日
 **/
public class HoldConfigDepend1{

    private MyConfigDepend1 myConfigDepend1;

    public void setMyConfigDepend1(MyConfigDepend1 myConfigDepend1){
        this.myConfigDepend1 = myConfigDepend1;
    }
}
