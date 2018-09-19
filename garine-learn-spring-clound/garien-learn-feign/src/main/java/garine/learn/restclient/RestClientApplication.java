package garine.learn.restclient;

import garine.learn.clients.DoingByRestClientService;
import garine.learn.restclient.annotation.EnableRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "garine.learn")
@EnableFeignClients(basePackages = "garine.learn")
@EnableScheduling
@EnableDiscoveryClient
@EnableRestClient(importClass = DoingByRestClientService.class)
public class RestClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RestClientApplication.class, args);
    }
}
