package garine.learn.common.sdk.annotation.support;

import garine.learn.common.sdk.annotation.EnableSuchSDKBeans;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

/**
 *
 * 基于参数构造方法注入属性
 * @author zhoujy
 * @date 2018年12月19日
 **/
public class SuchBeansRegistar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attrs = metadata
                .getAnnotationAttributes(EnableSuchSDKBeans.class.getName());
        final Class<?>[] beanClasses = attrs == null ? null
                : (Class<?>[]) attrs.get("configClasses");
        if (beanClasses == null){
            return;
        }

        for (Class<?> beanClass : beanClasses) {
            if (registry instanceof SingletonBeanRegistry){
                SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry) registry;
                try {
                    singletonBeanRegistry.registerSingleton(beanClass.getSimpleName(), beanClass.newInstance());
                    continue;
                } catch (InstantiationException | IllegalAccessException e) {
                    //TODO log error
                    Assert.isTrue(true,
                            "instance bean error, class " + beanClass.getName() + " for error" + e.getMessage());
                }
            }
            registerWithBeanDefinition(beanClass, registry);
        }
    }

    private void registerWithBeanDefinition(Class<?> beanClass, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        registry.registerBeanDefinition(beanClass.getSimpleName(), builder.getRawBeanDefinition());
    }
}
