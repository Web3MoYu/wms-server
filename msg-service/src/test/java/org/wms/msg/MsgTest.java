package org.wms.msg;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MsgTest {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "柳岩");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("wms.topic", "test.tt", msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "object.queue"),
            exchange = @Exchange(name = "wms.topic", type = ExchangeTypes.TOPIC),
            key = "test.*"
    ))
    public void receive(Map<String, Object> message) {
        System.out.println("消费者接收到object.queue消息：【" + message + "】");
    }
}
