package garine.learn.breaker;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "garine.learn")
@EnableDiscoveryClient
@EnableHystrix
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BreakerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BreakerApplication.class).run(args);
    }
}
