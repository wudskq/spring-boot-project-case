package cn.com.wudskq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenfangchao
 * @title: BootRabbitMqApplication
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/7 4:36 PM
 */
@EnableRabbit
@SpringBootApplication
public class BootRabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootRabbitMqApplication.class,args);
    }
}
