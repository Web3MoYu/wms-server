package org.wms.msg.controller;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.constant.MQConstant;
import org.wms.common.entity.msg.Msg;
import org.wms.common.enums.msg.MsgReadEnums;
import org.wms.common.enums.msg.MsgTypeEnums;
import org.wms.common.model.Result;
import org.wms.msg.consumer.MsgConsumer;
import org.wms.common.entity.msg.WsMsgDataVO;
import org.wms.common.enums.msg.MsgEnums;
import org.wms.msg.service.MsgService;
import org.wms.security.util.SecurityUtil;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Resource
    MsgService msgService;

    @Resource
    RabbitTemplate rabbitTemplate;


    /**
     * 统计当前用户是否含有未读的消息
     *
     * @return 是否存在未读消息
     */
    @GetMapping("/countUnReadMsg")
    @PreAuthorize("isAuthenticated()")
    public Result<Boolean> countUnReadMsg() {
        String userID = SecurityUtil.getUserID();
        Long count = msgService.lambdaQuery()
                .eq(Msg::getRecipientId, userID)
                .eq(Msg::getReadStatus, MsgReadEnums.UNREAD)
                .count();
        return Result.success(count > 0, "查询成功");
    }

    @GetMapping("/{id}")
    public String test(@PathVariable("id") String id) {
        Msg msg = new Msg();
        msg.setSenderName("测试");
        msg.setType(MsgTypeEnums.OTHER);
        msg.setTitle("测试发送");
        msg.setContent("你有一条订单需要审批");
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY,
                new WsMsgDataVO<>(msg, MsgEnums.NOTICE.getCode(), id));
        return "发送成功";
    }
}
