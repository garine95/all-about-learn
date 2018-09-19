package garine.learn.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    DoingByRestClientService doingByRestClientService;
    @Autowired
    DoingByFeignService doingByFeignService;

    @RequestMapping("sayHelloByRestClient")
    public String sayHelloByRestClient(){
        return doingByRestClientService.sayHello("Rest garine");
    }

    @RequestMapping("sayHelloByFeignClient")
    public String sayHelloByFeignClient(){
        return doingByFeignService.sayHello("Feign garine");
    }
}
