package garine.learn.ribbon.loadblance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "garine.learn.ribbon.loadblance")
@EnableDiscoveryClient
@RestController
public class TestRibbonApplocation {
    public static void main(String[] args) {
        SpringApplication.run(TestRibbonApplocation.class, args);
    }

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @GetMapping("/{applicationName}/ribbonService")
    public String ribbonService(@PathVariable("applicationName") String applicationName){
        return restTemplate.getForObject("http://" + applicationName+"/ribbonService", String.class);
    }
}
