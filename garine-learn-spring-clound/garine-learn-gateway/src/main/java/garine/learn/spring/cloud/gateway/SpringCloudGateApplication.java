package garine.learn.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

/**
 * @author zhoujy
 * @date 2018年09月27日
 **/
@SpringBootApplication(scanBasePackages = "garine.learn.spring.cloud.gateway")
public class SpringCloudGateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGateApplication.class, args);
    }

    /**
     * 配置方式1，方式2在yml
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/baidu")
                        .uri("http://www.baidu.com")
                ).build();
    }


}
