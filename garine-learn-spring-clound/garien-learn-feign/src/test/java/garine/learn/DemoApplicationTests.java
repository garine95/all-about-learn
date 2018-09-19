package garine.learn;

import garine.learn.clients.DoingByRestClientService;
import garine.learn.restclient.RestClientApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestClientApplication.class)
public class DemoApplicationTests {

	@Autowired
    DoingByRestClientService doingByRestClientService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void doingService(){
		doingByRestClientService.sayHello("hellogarine");
	}

}
