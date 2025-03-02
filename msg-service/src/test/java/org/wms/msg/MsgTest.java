package org.wms.msg;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MsgTest {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "1");
        msg.put("age", 1);
        msg.put("id", "1");
        // 发送消息
        rabbitTemplate.convertAndSend("wms.topic", "in.t", msg);
    }

}
