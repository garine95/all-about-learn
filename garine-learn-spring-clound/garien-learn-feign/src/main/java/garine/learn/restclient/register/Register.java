package garine.learn.restclient.register;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.Assert;

public abstract class Register {
    public BeanDefinitionRegistry beanDefinitionRegistry;

    public Register(BeanDefinitionRegistry beanDefinitionRegistry){
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * 注册RestClient动态代理实例
     * @param interfaceClass
     * @param instance
     * @return
     */
    public boolean register(Class<?> interfaceClass, Object instance){
        Assert.isTrue(interfaceClass.isInterface(), "注册时传入非接口");
        Assert.isTrue(instance != null, "注册时传入实例为空");
        return doRegister(interfaceClass, instance);
    }

    public abstract boolean doRegister(Class<?> interfaceClass, Object instance);
}
