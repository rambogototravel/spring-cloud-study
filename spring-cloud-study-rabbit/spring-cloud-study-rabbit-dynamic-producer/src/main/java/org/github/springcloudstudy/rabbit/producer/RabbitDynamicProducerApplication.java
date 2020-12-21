package org.github.springcloudstudy.rabbit.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding
@SpringBootApplication
public class RabbitDynamicProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitDynamicProducerApplication.class, args);
    }
}
