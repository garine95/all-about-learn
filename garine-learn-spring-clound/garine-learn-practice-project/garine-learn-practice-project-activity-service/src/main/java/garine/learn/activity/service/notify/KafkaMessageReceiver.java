package garine.learn.activity.service.notify;


import garine.learn.activity.service.dal.entitys.ActDrawNum;
import garine.learn.activity.service.dal.persistence.ActDrawNumMapper;
import garine.learn.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageReceiver {

    @Autowired
    ActDrawNumMapper actDrawNumMapper;

    @KafkaListener(topics = {"test"}, containerFactory = "ackBatchFactory")
    public void registryReceiver(ConsumerRecord<Integer, String> integerStringConsumerRecord, Acknowledgment acknowledgment, Consumer consumer) {
        UserBean userBean=null;
        try {
            //幂等校验
            String jsonStr=integerStringConsumerRecord.value();
            log.warn(jsonStr);
            userBean=(UserBean) JsonUtils.jsonToBean(integerStringConsumerRecord.value(),UserBean.class);
            ActDrawNum actDrawNum=new ActDrawNum();
            actDrawNum.setName(userBean.getName());
            actDrawNum.setNowNumber(0);
            actDrawNum.setUid(userBean.getUid());
            actDrawNum.setMaxNumber(1);
            actDrawNumMapper.inputDrawNumber(actDrawNum);
            acknowledgment.acknowledge();
        }catch (DuplicateKeyException e){
            //已经存在用户的抽奖资格记录，则累加抽奖机会
            actDrawNumMapper.updateMaxNumber(userBean.getUid());
        }catch(Exception e){
            throw new RuntimeException("消息处理异常:"+e);
        }
    }
}
