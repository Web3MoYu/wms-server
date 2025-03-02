package org.wms.msg.consumer;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.wms.msg.config.websocket.MyWebSocket;

import java.io.IOException;
import java.util.Map;

@Component
public class MsgConsumer {

    @Resource
    MyWebSocket webSocket;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "order.queue"),
            exchange = @Exchange(name = "wms.topic", type = ExchangeTypes.TOPIC),
            key = "in.*"
    ))
    public void receive(Map<String, Object> message) throws IOException {
        webSocket.sendMsg((String) message.get("id"), JSONUtil.toJsonStr(message));
        System.out.println("消费者接收到object.queue消息：【" + message + "】");
    }

}
