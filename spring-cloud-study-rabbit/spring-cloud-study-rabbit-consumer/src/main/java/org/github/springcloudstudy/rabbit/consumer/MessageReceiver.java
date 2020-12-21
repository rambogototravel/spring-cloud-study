package org.github.springcloudstudy.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author yangruibo
 */
@Slf4j
@Component
public class MessageReceiver {

    @StreamListener(value = Sink.INPUT, condition = "headers['code']=='ams'")
    public void receiveTime(@Payload Message<String> message, @Header("code") String code) {
        log.info("接收[{}]消息:{}", code, message.getPayload());
    }
}
