package garine.learn.user.sso.config;


import garine.learn.user.sso.api.UserCoreServiceRemote;
import garine.learn.user.sso.interceptor.TokenIntercepter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


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
