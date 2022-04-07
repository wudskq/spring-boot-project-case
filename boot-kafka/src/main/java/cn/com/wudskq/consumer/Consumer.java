package cn.com.wudskq.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Value("${wudskq.kafka.topic.consumer.test3}")
    private String topic3;


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


    //配置的第二个接入的消费者
    //设置批量拉取poll
    @KafkaListener(topics = "${wudskq.kafka.topic.test2}", groupId = "${wudskq.kafka.topic.group}",
            containerFactory = "kafkaTwoContainerFactory")
    public void listenerTwo(List<ConsumerRecord<?, ?>> record) {
        record.forEach(consumerRecord -> {
            logger.info("第二个消费者收到 "+topic2+ "的消息 "+consumerRecord.value());
        });
    }

    //kafka分区消费
    @KafkaListener(
            groupId = "${wudskq.kafka.topic.group}",
            topicPartitions = {
                    @TopicPartition(
                            topic = "${wudskq.kafka.topic.consumer.test3}",
                            partitions = {"#{'${wudskq.kafka.topic.consumer.partitions0}'.split(',')}"}
                    )
            }
    )
    public void listenerPartitions0(ConsumerRecord<?, ?> record) {
        logger.info("第一个消费者收到 "+topic3+"分区"+"partitions0的消息 "+record.value());
    }

    //kafka分区消费
    @KafkaListener(
            groupId = "${wudskq.kafka.topic.group}",
            topicPartitions = {
                    @TopicPartition(
                            topic = "${wudskq.kafka.topic.consumer.test3}",
                            partitions = {"#{'${wudskq.kafka.topic.consumer.partitions1}'.split(',')}"}
                    )
            }
    )
    public void listenerPartitions1(ConsumerRecord<?, ?> record) {
        logger.info("第一个消费者收到 "+topic3+"分区"+"partitions1的消息 "+record.value());
    }
}
