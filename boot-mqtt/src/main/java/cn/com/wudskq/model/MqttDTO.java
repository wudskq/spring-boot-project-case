package cn.com.wudskq.model;

import java.io.Serializable;

/**
 * @author chenfangchao
 * @version 1.0.0
 * @ClassName MqttDTO.java
 * @Description TODO
 * @createTime 2021年10月29日 10:18:00
 */

public class MqttDTO implements Serializable {

    private static final long serialVersionUID = 2127171152435094427L;

    private String topic;

    private String payload;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
