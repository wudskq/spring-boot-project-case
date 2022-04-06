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


    @Value("${wudskq.kafka.topic.test0}")
    private String topic0;

    @Value("${wudskq.kafka.topic.test1}")
    private String topic1;

    @Value("${wudskq.kafka.topic.test2}")
    private String topic2;


    //默认消费
    @KafkaListener(topics = "${wudskq.kafka.topic.test0}", groupId = "${wudskq.kafka.topic.group}")
    public void consumer1(ConsumerRecord<?, ?> record){
        Object value = record.value();
        logger.info("默认消费者收到 "+topic0+ "的消息 "+ value);
    }

    //配置的第一个消费者
    @KafkaListener(topics = "${wudskq.kafka.topic.test1}", groupId = "${wudskq.kafka.topic.group}",
            containerFactory = "kafkaOneContainerFactory")
    public void listenerOne(ConsumerRecord<?, ?> record) {
        logger.info("第一个消费者收到 "+topic1+ "的消息 "+record.value());
    }

}
