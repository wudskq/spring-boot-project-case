package cn.com.wudskq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author chenfangchao
 * @title: ProducerController
 * @projectName boot-project
 * @description: TODO 生产者正常在业务逻辑中,再此简单示例
 * @date 2022/4/7 3:08 AM
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    Logger log = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

    @Autowired
    @Qualifier(value = "kafkaOneTemplate")
    private KafkaTemplate kafkaOneTemplate;

    @Autowired
    @Qualifier(value = "kafkaTwoTemplate")
    private KafkaTemplate kafkaTwoTemplate;

    @Value("${wudskq.kafka.topic.test0}")
    private String topic0;

    @Value("${wudskq.kafka.topic.test1}")
    private String topic1;

    @Value("${wudskq.kafka.topic.test2}")
    private String topic2;

    @Value("${wudskq.kafka.topic.consumer.test3}")
    private String topic3;


//    //默认生产者
//    @GetMapping("/0")
//    public void produce(){
//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic0, "producer0");
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                //发送失败的处理
//                log.info(topic0 + " - 生产者 发送消息失败：" + throwable.getMessage());
//            }
//            @Override
//            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
//                //成功的处理
//                log.info(topic0 + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
//            }
//        });
//    }

    //配置的第一个生产者
    @GetMapping("/1")
    public void produce1(){
        ListenableFuture<SendResult<String, Object>> future = kafkaOneTemplate.send(topic1, "producer1");
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(topic1 + " - 生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(topic1 + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    //配置的第二个生产者
    @GetMapping("/2")
    public void produce2(){
        ListenableFuture<SendResult<String, Object>> future = kafkaTwoTemplate.send(topic2, "producer2");
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(topic2 + " - 生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(topic2 + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    //分区发送消息
    @GetMapping("/partitions")
    public void producePartitions0(){
        ListenableFuture<SendResult<String, Object>> future = kafkaOneTemplate.send(topic3, "partitions");
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(topic3 + " - 生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(topic3 + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
    
}
