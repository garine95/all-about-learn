package garine.learn;

import garine.learn.common.sdk.annotation.EnableDistributeLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "garine.learn")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "garine.learn.activity.service.api")
@EnableDistributeLock
public class ActivityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityServiceApplication.class, args);
	}
}
