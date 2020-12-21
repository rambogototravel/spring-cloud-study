package org.github.springcloudstudy.rabbit.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class SpringCloudStudyRabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStudyRabbitConsumerApplication.class, args);
    }
}
