package org.github.springcloudstudy.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.github.springcloudstudy.rabbit.common.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author yangruibo
 */
@Slf4j
@Component
public class MessageReceiver {

    @Value("${spring.profiles.active:dev}")
    private String active;

    /*********************************动态通道选择示例******************************/
    @StreamListener(value = MessageSubscribe.DYNAMIC1_CHANNEL)
    public void dynamic1Receiver(@Payload User user) {
        log.info("Received-{} from {} channel age: {}", active, MessageSubscribe.DYNAMIC1_CHANNEL, user.getAge());
    }

    @StreamListener(value = MessageSubscribe.DYNAMIC2_CHANNEL)
    public void dynamic2Receiver(@Payload User user) {
        log.info("Received-{} from {} channel age: {}", active, MessageSubscribe.DYNAMIC2_CHANNEL, user.getAge());
    }
}
