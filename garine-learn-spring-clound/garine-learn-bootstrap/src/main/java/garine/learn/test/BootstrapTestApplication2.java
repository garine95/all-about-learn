package garine.learn.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableAutoConfiguration
public class BootstrapTestApplication2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.setId("mycontext");
        annotationConfigApplicationContext.refresh();
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BootstrapTestApplication2.class);
        builder.parent(annotationConfigApplicationContext).run(args);
    }
}
