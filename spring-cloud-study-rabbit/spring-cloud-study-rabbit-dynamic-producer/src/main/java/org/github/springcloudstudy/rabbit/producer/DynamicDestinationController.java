package org.github.springcloudstudy.rabbit.producer;

import org.apache.commons.lang3.StringUtils;
import org.github.springcloudstudy.rabbit.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangruibo
 */
@Controller
public class DynamicDestinationController {

    @Autowired
    private BinderAwareChannelResolver resolver;

    /************************************方式一************************************/
    @RequestMapping(path = "/way1/{dest}", method = RequestMethod.POST, consumes = "*/*")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest1(@PathVariable("dest") String dest, @RequestBody String body) {
        sendMessage(body, dest);
    }

    private void sendMessage(String body, String dest) {
        resolver.resolveDestination(dest).send(MessageBuilder.createMessage(body,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, "application/json"))));
    }


    /************************************方式二************************************/

    @Resource(name = "router-channel")
    private MessageChannel messageChannel;

    @PostMapping(path = "/way2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest2(@RequestBody User body, @RequestHeader(name = "dest", required = false) String dest) {
        Map<String, Object> headers = new HashMap<>(2);
        headers.put(MessageHeaders.CONTENT_TYPE, "application/json");
        if (!StringUtils.isEmpty(dest)) {
            headers.put("dest", dest);
        }
        sendMessage(body, headers);
    }

    private void sendMessage(User body, Map<String, Object> headers) {
        messageChannel.send(MessageBuilder.createMessage(body, new MessageHeaders(headers)));
    }

    @Bean(name = "router-channel")
    public MessageChannel routerChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "router-channel")
    public ExpressionEvaluatingRouter router() {
        ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter(new SpelExpressionParser().parseExpression("headers[dest]"));
        //作用于通过spel表达式没有获取到对应的通道信息
        router.setDefaultOutputChannelName("dynamic1-channel");
        router.setChannelResolver(resolver);
        return router;
    }
}
