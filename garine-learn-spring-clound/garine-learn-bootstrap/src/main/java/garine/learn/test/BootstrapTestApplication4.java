package garine.learn.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "garine.learn.test")
public class BootstrapTestApplication4 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BootstrapTestApplication4.class, args);
    }
}
