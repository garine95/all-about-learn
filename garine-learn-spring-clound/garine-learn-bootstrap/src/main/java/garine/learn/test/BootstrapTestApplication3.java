package garine.learn.test;

import garine.learn.test.auwired.TestAutowiredRegistar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "garine.learn.test.auwired")
@Import(TestAutowiredRegistar.class)
public class BootstrapTestApplication3 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BootstrapTestApplication3.class, args);
    }
}
