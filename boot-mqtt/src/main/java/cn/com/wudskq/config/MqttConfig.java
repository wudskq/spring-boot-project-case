package cn.com.wudskq.config;



import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenfangchao
 * @version 1.0.0
 * @ClassName SpringMqttConfig.java
 * @Description TODO
 * @createTime 2021年10月29日 03:17:00
 */
@Component
@Configuration
@ConditionalOnProperty(value = "mqtt.enabled", havingValue = "true")
public class MqttConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.userName}")
    private String userName;

    @Value("${mqtt.passWord}")
    private String passWord;

    @Value("${mqtt.keepLiveTime}")
    private Integer keepLiveTime;

    @Value("${mqtt.cilentId}")
    private String cilentId;

    @Value("${mqtt.topic.topic0}")
    private String faceTopic;

    @Value("${mqtt.topic.topic1}")
    private String carTopic;


    /*****
     * 创建MqttPahoClientFactory，设置MQTT Broker连接属性，如果使用SSL验证，也在这里设置。
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{host});
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setKeepAliveInterval(keepLiveTime);
        options.setAutomaticReconnect(true);
        /* 连接断开后清除会话,下次连接重新建立会话 */
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(cilentId,
                mqttClientFactory(), faceTopic, carTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("thread-pool-mqtt").build();

    /**
     * 使用线程池初始化kafka-sdk服务,消费MQTT数据
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(9, 10, 5,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    @Bean
    //ServiceActivator注解表明当前方法用于处理MQTT消息，inputChannel参数指定了用于接收消息信息的channel。
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            threadPoolExecutor.execute(() -> {
                String payload = message.getPayload().toString();
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                // 根据topic分别进行消息处理。
                if (topic.equals(faceTopic)) {
                    System.out.println("处理消息------>" + faceTopic);
                    logger.info("处理业务逻辑1");
                } else if (topic.equals(carTopic)) {
                    System.out.println("处理消息------>" + carTopic);
                    logger.info("处理业务逻辑2");
                } else {
                    System.out.println("丢弃消息------>" + topic);
                    logger.info("丢弃消息");
                }
            });
        };
    }


    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /*****
     * 发送消息和消费消息Channel可以使用相同MqttPahoClientFactory
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler outbound() {
        // 在这里进行mqttOutboundChannel的相关设置
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("publishClient", mqttClientFactory());
        messageHandler.setAsync(true); //如果设置成true，发送消息时将不会阻塞。
        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {
        // 定义重载方法，用于消息发送
        void sendToMqtt(String payload);
        // 指定topic进行消息发送
        void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);
        void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFaceTopic() {
        return faceTopic;
    }

    public void setFaceTopic(String faceTopic) {
        this.faceTopic = faceTopic;
    }

    public Integer getKeepLiveTime() {
        return keepLiveTime;
    }

    public void setKeepLiveTime(Integer keepLiveTime) {
        this.keepLiveTime = keepLiveTime;
    }

    public String getCarTopic() {
        return carTopic;
    }

    public void setCarTopic(String carTopic) {
        this.carTopic = carTopic;
    }
}
