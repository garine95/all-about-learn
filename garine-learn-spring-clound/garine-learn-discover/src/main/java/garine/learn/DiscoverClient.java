package garine.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

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
    public String hello(@RequestParam("name") String name){
        return "hello too" + name;
    }

    @RequestMapping("/ribbonService")
    public String ribbonService(){
        System.out.println("to here");
        return "hello too ribbon";
    }
}
