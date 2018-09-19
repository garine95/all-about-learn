package garine.learn.clients;

import garine.learn.restclient.annotation.RestClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 自定义屌丝版restClient实现的调用
 */
@RestClient(applicationName = "discovery-service")
public interface DoingByRestClientService {

    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name") String world);

}
