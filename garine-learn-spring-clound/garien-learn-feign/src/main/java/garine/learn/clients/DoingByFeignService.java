package garine.learn.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign实现的调用
 */
@FeignClient(name = "discovery-service")
public interface DoingByFeignService {

    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name") String world);

}
