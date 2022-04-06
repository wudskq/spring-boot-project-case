package cn.com.wudskq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${wudskq.kafka.topic.test1}")
    private String topic1;
    
    @GetMapping("/1")
    public void produce1(){
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic1, "producer1");
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
    
}