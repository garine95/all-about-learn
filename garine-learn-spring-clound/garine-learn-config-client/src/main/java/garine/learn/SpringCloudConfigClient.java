package garine.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClient.class, args);
    }
    @Value("${name}")
    String name;

    @RequestMapping("/getName")
    public String getName(){
        return name;
    }
}
