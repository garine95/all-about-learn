package garine.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TestEnableModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestEnableModelApplication.class, args);
	}
}
