package cn.com.wudskq.controller;

import cn.com.wudskq.config.MqttConfig;
import cn.com.wudskq.model.MqttDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfangchao
 */
@RestController
@ConditionalOnProperty(value = "mqtt.enabled", havingValue = "true")
@RequestMapping("/mqtt")
public class MqttController {

    private Logger logger = LoggerFactory.getLogger(MqttController.class);

    @Autowired
    private MqttConfig.MqttGateway mqttGateway;


    @PostMapping("/send")
    public String send(@RequestBody MqttDTO message) {
        mqttGateway.sendToMqtt(message.getTopic(), message.getPayload());
        return "send message : " + message;
    }
}