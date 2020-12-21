package org.github.springcloudstudy.rabbit.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(MessageSubscribe.class)
@SpringBootApplication
public class RabbitDynamicConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitDynamicConsumerApplication.class, args);
    }
}
