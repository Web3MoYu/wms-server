package org.wms.msg.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.msg.Msg;
import org.wms.common.enums.msg.MsgReadEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.msg.service.MsgService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

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

    /**
     * 分页查询消息
     *
     * @param senderId   发送者ID
     * @param type       消息类型
     * @param title      消息标题
     * @param readStatus 阅读状态
     * @param priority   优先级
     */
    @GetMapping("/page")
    @PreAuthorize("isAuthenticated()")
    public Result<Page<Msg>> page(
            String senderId,
            String type,
            String title,
            Integer readStatus,
            Integer priority,
            LocalDateTime startTime,
            LocalDateTime endTime,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        String userId = SecurityUtil.getUserID();
        Page<Msg> pageResult = msgService.lambdaQuery()
                .eq(Msg::getRecipientId, userId)
                .eq(StringUtils.hasText(senderId), Msg::getSenderId, senderId)
                .eq(StringUtils.hasText(type), Msg::getType, type)
                .like(StringUtils.hasText(title), Msg::getTitle, title)
                .eq(readStatus != null, Msg::getReadStatus, readStatus)
                .eq(priority != null, Msg::getPriority, priority)
                .ge(startTime != null, Msg::getSendTime, startTime)
                .le(endTime != null, Msg::getSendTime, endTime)
                .orderByAsc(Msg::getReadStatus)
                .orderByDesc(Msg::getSendTime)
                .page(new Page<>(page, pageSize));

        return Result.success(pageResult, "查询成功");
    }

    /**
     * 获取未读消息
     *
     * @return 未读消息列表
     */
    @GetMapping("/getUnReadMsg")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Msg>> getUnReadMsg() {
        String userId = SecurityUtil.getUserID();

        List<Msg> unreadMsgs = msgService.lambdaQuery()
                .eq(Msg::getRecipientId, userId)
                .eq(Msg::getReadStatus, MsgReadEnums.UNREAD.getCode())
                .orderByDesc(Msg::getSendTime)
                .last("LIMIT 10")
                .list();

        return Result.success(unreadMsgs, "查询成功");
    }

    /**
     * 获取已读消息
     *
     * @return 已读消息列表
     */
    @GetMapping("/getReadMsg")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Msg>> getReadMsg() {
        String userId = SecurityUtil.getUserID();

        List<Msg> readMsgs = msgService.lambdaQuery()
                .eq(Msg::getRecipientId, userId)
                .eq(Msg::getReadStatus, MsgReadEnums.READ.getCode())
                .orderByDesc(Msg::getSendTime)
                .last("LIMIT 10")
                .list();

        return Result.success(readMsgs, "查询成功");
    }

    /**
     * 读消息
     *
     * @param id id
     * @return 返回结果
     */
    @PutMapping("/read/{id}")
    @PreAuthorize("isAuthenticated()")
    public Result<String> read(@PathVariable("id") String id) {
        boolean update = msgService.lambdaUpdate()
                .eq(Msg::getId, id)
                .set(Msg::getReadStatus, MsgReadEnums.READ.getCode())
                .set(Msg::getReadTime, LocalDateTime.now())
                .update();
        if (!update) {
            throw new BizException("修改状态失败");
        }
        return Result.success(null, "修改状态成功");
    }
}
