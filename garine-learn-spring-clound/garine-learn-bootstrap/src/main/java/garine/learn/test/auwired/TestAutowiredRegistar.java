package garine.learn.test.auwired;

import garine.learn.test.MyConfig;
import garine.learn.test.MyConfigDepend1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author zhoujy
 * @date 2018年09月18日
 **/
@Component
public class TestAutowiredRegistar implements ImportBeanDefinitionRegistrar,BeanFactoryAware,InitializingBean{
    private BeanFactory beanFactory;

    @Autowired
    TestRegistarDependOn testRegistarDependOn;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //@Autowired注解此时还没处理，依赖还没注入，只能手动调用getBean进行依赖实例的初始化
        //MyConfig myConfig = beanFactory.getBean(MyConfig.class);
        //System.out.println(myConfig);
        System.out.println(TestAutowiredRegistar.class.getSimpleName() + "-----" +testRegistarDependOn);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(TestAutowiredRegistar.class.getSimpleName() + "-----" + testRegistarDependOn);
    }
}
