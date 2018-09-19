package garine.learn.restclient.register.impl;

import garine.learn.restclient.register.Register;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.lang.Nullable;

/**
 * 工厂方法实现动态代理bean注册
 */
public class FactoryBeanRegister extends Register {

    public FactoryBeanRegister(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    @Override
    public boolean doRegister(Class<?> interfaceClass, Object instance) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(RestClientFActoryBean.class);
        beanDefinition.setAttribute("objectType", interfaceClass);
        beanDefinition.setAttribute("instance", instance);
        beanDefinitionRegistry.registerBeanDefinition("RestClient." + interfaceClass.getSimpleName(), beanDefinition);
        return true;
    }


    class RestClientFActoryBean implements FactoryBean{
        private Class<?> objectType;

        private Object instance;

        @Nullable
        @Override
        public Object getObject() throws Exception {
            return null;
        }

        @Nullable
        @Override
        public Class<?> getObjectType() {
            return null;
        }
    }
}
