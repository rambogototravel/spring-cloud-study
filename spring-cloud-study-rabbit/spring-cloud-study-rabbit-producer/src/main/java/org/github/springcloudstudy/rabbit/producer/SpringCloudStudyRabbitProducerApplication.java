package org.github.springcloudstudy.rabbit.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableBinding
@SpringBootApplication
public class SpringCloudStudyRabbitProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStudyRabbitProducerApplication.class, args);
    }

    private String format = "yyyy-mm-dd  HH:mm:ss";

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "2000", maxMessagesPerPoll = "1"))
    public MessageSource<String> sendTime() {
        String message = new SimpleDateFormat(format).format(new Date());

        log.info("发送消息：{}", message);

        Map<String, Object> map = new HashMap<>();
        map.put("code", "ams");
        map.put("step", "init");
        return () -> new GenericMessage<>(message, map);
    }
}
