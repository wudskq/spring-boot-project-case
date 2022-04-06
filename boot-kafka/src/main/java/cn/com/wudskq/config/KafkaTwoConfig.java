package cn.com.wudskq.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenfangchao
 * @title: KafkaTwoConfig
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/7 5:13 AM
 */
@Configuration
public class KafkaTwoConfig {

    //集群服务
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //是否自动提交
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    //自动提交的时间间隔 ms
    private final int autoCommitInterval = 1000;

    //下次读取topic位置
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    //批量拉取记录数
    private final String maxPollRecordsConfig = "100";

    public static final  String USER_NAME  = "admin";

    public static final  String PASS_WORD  = "admin";


    @Bean(value = "kafkaTwoTemplate")
    public KafkaTemplate<String, String> kafkaTwoTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaTwoContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    private ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //protocol
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SASL_PLAINTEXT");
        //sasl
        props.put(SaslConfigs.SASL_MECHANISM,"SCRAM-SHA-512");
        String jassc = "org.apache.kafka.common.security.scram.ScramLoginModule required\n"
                + "username = \"" + USER_NAME + "\"\n"
                + "password =\"" + PASS_WORD + "\";";
        props.put(SaslConfigs.SASL_JAAS_CONFIG,jassc);
        return props;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //poll record 批量拉取数
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,maxPollRecordsConfig);
        //protocol
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SASL_PLAINTEXT");
        //sasl
        props.put(SaslConfigs.SASL_MECHANISM,"SCRAM-SHA-512");
        String jassc = "org.apache.kafka.common.security.scram.ScramLoginModule required\n"
                + "username = \"" + USER_NAME + "\"\n"
                + "password =\"" + PASS_WORD + "\";";
        props.put(SaslConfigs.SASL_JAAS_CONFIG,jassc);
        //listener-监听线程池
        props.put("concurrency",5);
        props.put("ackMode","manual_immediate");
        props.put("topicsFatal",false);
        return props;
    }
}
