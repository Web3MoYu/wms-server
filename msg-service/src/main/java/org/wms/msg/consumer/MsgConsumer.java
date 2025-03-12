package org.wms.msg.consumer;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.wms.msg.config.websocket.MyWebSocket;
import org.wms.common.entity.msg.WsMsgDataVO;

import java.io.IOException;

@Component
public class MsgConsumer {

    public static final String EXCHANGE_NAME = "wms.topic";

    public static final String QUEUE_NAME = "msg.queue";

    public static final String ROUTING_KEY = "msg.receive";
    private static final Logger log = LoggerFactory.getLogger(MsgConsumer.class);

    @Resource
    MyWebSocket webSocket;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QUEUE_NAME),
            exchange = @Exchange(name = EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
            key = ROUTING_KEY
    ))
    public void receive(WsMsgDataVO<Boolean> message) throws IOException {
        webSocket.sendMsg(message.getId(), JSONUtil.toJsonStr(message));
        MsgConsumer.log.info("消费者接收到消息：{}", JSONUtil.toJsonStr(message));
    }

}
