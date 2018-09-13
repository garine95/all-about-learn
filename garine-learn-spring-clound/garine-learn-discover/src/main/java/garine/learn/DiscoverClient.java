package garine.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DiscoverClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(DiscoverClient.class, args);
    }

    @RequestMapping("/services")
    public Object getServices(){
        return discoveryClient.getServices();
    }

    @RequestMapping("/instance")
    public Object getInstance(@RequestParam("name") String name){
        return discoveryClient.getInstances(name);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello garine";
    }
}