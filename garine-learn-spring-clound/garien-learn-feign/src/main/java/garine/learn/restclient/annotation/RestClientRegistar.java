package garine.learn.restclient.annotation;

import garine.learn.restclient.proxy.RestClientInvocationHandler;
import garine.learn.restclient.register.Register;
import garine.learn.restclient.register.impl.FactoryBeanRegister;
import garine.learn.restclient.register.impl.SingletonRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RestClientRegistar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {
    private BeanFactory beanFactory;

    /**
     * 注册动态代理实现bean的BeanDefinetion
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?>[] importClass = getImportClass(importingClassMetadata);
        Stream.of(importClass).filter(Class::isInterface).filter(clazz -> clazz.isAnnotationPresent(RestClient.class)).forEach(val -> {
            //构建动态代理对象
            Register register = new SingletonRegister(registry);
            Object dymicInstance = createRestDymicInstance(val, beanFactory);
            if (!register.register(val, dymicInstance)){
                new FactoryBeanRegister(registry).register(val, dymicInstance);
            }
        });
    }


    /**
     * 为被@RestClient注解的对创建动态代理对象
     * @param val
     * @param beanFactory
     * @return
     */
    private Object createRestDymicInstance(Class val, BeanFactory beanFactory) {
        RestClient restClient = (RestClient) val.getAnnotation(RestClient.class);
        Object proxyInstance = Proxy.newProxyInstance(val.getClassLoader(), new Class[]{val}, new RestClientInvocationHandler(beanFactory, restClient));
        return proxyInstance;
    }

    private Class<?>[]  getImportClass(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes =  importingClassMetadata.getAnnotationAttributes(EnableRestClient.class.getName());
        Class<?>[] importList = (Class<?>[]) annotationAttributes.get("importClass");
        if (importList != null && importList.length > 0){
            //优先导入类设置
            return importList;
        }
        String[] packagePaths = (String[])annotationAttributes.get("scanbasePackages");
        if (packagePaths != null && packagePaths.length > 0){
            //扫描路径的带有@RestClient注解的类信息

            return new Class<?>[]{};
        }
        throw new RuntimeException("没有RestClient配置信息");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
