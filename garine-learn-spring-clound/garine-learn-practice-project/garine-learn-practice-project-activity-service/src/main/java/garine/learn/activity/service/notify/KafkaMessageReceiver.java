package garine.learn.activity.service.notify;


import com.google.common.base.Throwables;
import garine.learn.activity.service.dal.entitys.ActivityDrawNum;
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

import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class KafkaMessageReceiver {

    @Autowired
    ActDrawNumMapper actDrawNumMapper;

    /**
     * listenerContainerFactory设置了批量拉取消息，因此参数是List<ConsumerRecord<Integer, String>>，否则是ConsumerRecord
     * @param integerStringConsumerRecords
     * @param acknowledgment
     */
    @KafkaListener(topics = {"test"}, containerFactory = "listenerContainerFactory")
    public void registryReceiver(List<ConsumerRecord<Integer, String>> integerStringConsumerRecords, Acknowledgment acknowledgment, Consumer consumer) {
        Iterator<ConsumerRecord<Integer, String>> it = integerStringConsumerRecords.iterator();
        while (it.hasNext()){
            ConsumerRecord<Integer, String> consumerRecords = it.next();
            registryHandler(consumerRecords, acknowledgment);
        }
    }

    private void registryHandler(ConsumerRecord<Integer, String> integerStringConsumerRecord, Acknowledgment acknowledgment) {
        UserBean userBean=null;
        try {
            //幂等校验
            String jsonStr=integerStringConsumerRecord.value();
            log.warn(jsonStr);
           userBean=JsonUtils.jsonToBean(integerStringConsumerRecord.value(),UserBean.class);
            ActivityDrawNum activityDrawNum =new ActivityDrawNum();
            activityDrawNum.setName(userBean.getUsername());
            activityDrawNum.setNowNumber(0);
            activityDrawNum.setUid(userBean.getId());
            activityDrawNum.setMaxNumber(1);
            actDrawNumMapper.insertSelective(activityDrawNum);
            acknowledgment.acknowledge();
        }catch (DuplicateKeyException e){
            //已经存在用户的抽奖资格记录，则累加抽奖机会
            actDrawNumMapper.updateMaxNumber(userBean.getId());
            acknowledgment.acknowledge();
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
            throw new RuntimeException("消息处理异常:"+e);
        }
    }
}
