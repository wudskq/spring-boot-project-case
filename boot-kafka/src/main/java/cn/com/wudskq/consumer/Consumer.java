package cn.com.wudskq.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author chenfangchao
 * @title: Consumer
 * @projectName boot-project
 * @description: TODO 消费者
 * @date 2022/4/7 4:23 AM
 */
@Component
public class Consumer {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wudskq.kafka.topic.test1}")
    private String topic1;

    //默认消费
    @KafkaListener(topics = "${wudskq.kafka.topic.test1}", groupId = "${wudskq.kafka.topic.group}")
    public void consumer1(ConsumerRecord<?, ?> record){
        Object value = record.value();
        logger.info("收到 "+topic1+ "的消息 "+ value);
    }

}
