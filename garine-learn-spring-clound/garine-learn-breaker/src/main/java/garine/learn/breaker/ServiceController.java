package garine.learn.breaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import garine.learn.breaker.annotation.BreakerCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/hello/{name}")
    @BreakerCommand(strategy = "BREAKTIME")
    public String hello(@PathVariable String name) throws InterruptedException {
        Thread.sleep(100);
        return "hi.this is breaker service: " + name;
    }

    @GetMapping("/hello2/{name}")
    @BreakerCommand(strategy = "SEMAPHORE")
    public String hello2(@PathVariable String name) throws InterruptedException {
        Thread.sleep(2000);
        return "hi.this is breaker service: " + name;
    }
    @GetMapping("/helloWithHystrix")
    @HystrixCommand(fallbackMethod = "helloWithHystrixFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            })
    public String helloWithHystrix(){
        return "hi.this is Hystrix service";
    }

    /**
     * 服务熔断回调函数
     * @return
     */
    public String helloWithHystrixFallBack(){
        return "hi.this is HystrixFallback service";
    }
}
