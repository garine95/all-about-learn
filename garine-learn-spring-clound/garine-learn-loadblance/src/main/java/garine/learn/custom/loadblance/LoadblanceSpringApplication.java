package garine.learn.custom.loadblance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "garine.learn.custom.loadblance")
@EnableDiscoveryClient
@EnableScheduling
public class LoadblanceSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadblanceSpringApplication.class, args);
    }
}
