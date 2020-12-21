package org.github.springcloudstudy.rabbit.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author yangruibo
 */
public interface MessageSubscribe {

    /*********************************动态通道选择示例******************************/
    String DYNAMIC1_CHANNEL = "dynamic1-channel";
    String DYNAMIC2_CHANNEL = "dynamic2-channel";

    @Input(DYNAMIC1_CHANNEL)
    SubscribableChannel dynamic1Input();

    @Input(DYNAMIC2_CHANNEL)
    SubscribableChannel dynamic2Input();
}
