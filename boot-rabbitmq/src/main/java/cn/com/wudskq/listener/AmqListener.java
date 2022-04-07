package cn.com.wudskq.listener;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class AmqListener {

    public static Logger log = LoggerFactory.getLogger(AmqListener.class.getName());


    @Value("${infovision-rabbit.groupId}")
    private String groupId;

    private final String QUEUE_PREFIX = "taiji_";

    /* 动态生成queue*/
    @Bean
    public org.springframework.amqp.core.Queue queue(){
        long currentTimeMillis = System.currentTimeMillis();
        groupId = groupId.replaceAll("-", "_");
        log.info("本次rabbitMq动态生成消费者组ID为------------>"+groupId);
        return new org.springframework.amqp.core.Queue(groupId,false,true,true);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value =QUEUE_PREFIX+"xalarm_aps_exchange_consumer_queue_"+"#{queue.name}",
                    durable = "false", exclusive ="true",autoDelete = "true"),
            exchange = @Exchange(value = "xalarm_aps_exchange_forward_to_component", type = ExchangeTypes.DIRECT),
            key = "iservice1")
    )
    public void processServiceLogic0(String content) {
        log.info(content.toString());
        log.info("-----------处理业务逻辑Logic0--------------");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value =QUEUE_PREFIX+"xalarm_aps_exchange_consumer_queue_"+"#{queue.name}",
                    durable = "false" ,exclusive ="true",autoDelete = "true"),
            exchange = @Exchange(value = "xalarm_aps_exchange_forward_to_component", type = ExchangeTypes.DIRECT),
            key = "iservice2")
    )
    public void processServiceLogic1(String content) {
        log.info(content.toString());
        log.info("-----------处理业务逻辑Logic1--------------");
    }


}
