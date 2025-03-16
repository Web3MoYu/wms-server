package org.wms.msg.consumer;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.msg.Msg;
import org.wms.common.enums.msg.MsgIsSystemEnums;
import org.wms.msg.config.websocket.MyWebSocket;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.msg.service.MsgService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;


@Component
public class MsgConsumer {

    private static final Logger log = LoggerFactory.getLogger(MsgConsumer.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    MsgService msgService;

    @Resource
    MyWebSocket webSocket;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.QUEUE_NAME),
            exchange = @Exchange(name = MQConstant.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
            key = MQConstant.ROUTING_KEY
    ), ackMode = "AUTO")
    public void receive(WsMsgDataVO<Msg> message) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        // 插入消息记录表
        try {
            Msg msg = message.getData();
            msg.setSendTime(LocalDateTime.now());
            msg.setIsSystem(MsgIsSystemEnums.YES);
            msg.setCreateTime(LocalDateTime.now());
            msg.setUpdateTime(LocalDateTime.now());
            Msg one = msgService.lambdaQuery().eq(Msg::getRelatedBizId, msg.getRelatedBizId()).one();
            if (Objects.isNull(one)) {
                msgService.save(msg);
            }
            webSocket.sendMsg(message.getId(), objectMapper.writeValueAsString(message));

            MsgConsumer.log.info("消费者接收到消息：{}", JSONUtil.toJsonStr(message));
        } catch (Exception e) {
            log.error("消费者投递消息失败:{}", e.getMessage());
        }
    }

}
