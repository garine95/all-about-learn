package garine.learn.activity.web.config;

import garine.learn.activity.web.api.UserCoreServiceRemote;
import garine.learn.activity.web.intercept.TokenIntercepter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


/**
 * 在WebMvcConfigurerComposite中注入WebMvcConfigurer进行配置
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    UserCoreServiceRemote userCoreServiceRemote;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenIntercepter(userCoreServiceRemote));
    }
}
