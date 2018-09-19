package garine.learn.restclient.register.impl;

import garine.learn.restclient.register.Register;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * 直接SingletonRegistry实现bean注册
 */
public class SingletonRegister extends Register {

    public SingletonRegister(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    @Override
    public boolean doRegister(Class<?> interfaceClass, Object instance) {
        if (beanDefinitionRegistry instanceof SingletonBeanRegistry){
            SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry) beanDefinitionRegistry;
            singletonBeanRegistry.registerSingleton("RestClient." + interfaceClass.getSimpleName(), instance);
            return true;
        }
        return false;
    }
}
