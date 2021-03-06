package garine.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "garine.learn")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "garine.learn.activity.web.api")
public class ActivityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityWebApplication.class, args);
	}
}
