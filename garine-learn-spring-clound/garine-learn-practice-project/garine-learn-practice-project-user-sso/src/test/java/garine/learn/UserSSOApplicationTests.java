package garine.learn;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserSSOApplication.class})
public class UserSSOApplicationTests {

	@Resource
	KafkaTemplate kafkaTemplate;
	@Test
	public void testKafkaSendMsg() {
		kafkaTemplate.send("test", 0,"ss","1222");
	}

}
