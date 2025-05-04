package org.wms.msg.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.msg.Msg;
import org.wms.common.enums.msg.MsgReadEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.msg.model.dto.MsgPageDto;
import org.wms.msg.service.MsgService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Resource
    MsgService msgService;

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
     */
    @PostMapping("/page")
    @PreAuthorize("isAuthenticated()")
    public Result<Page<Msg>> page(@RequestBody MsgPageDto dto) {

        String userId = SecurityUtil.getUserID();
        Page<Msg> pageResult = msgService.lambdaQuery()
                .eq(Msg::getRecipientId, userId)
                .eq(StringUtils.hasText(dto.getSenderId()), Msg::getSenderId, dto.getSenderId())
                .eq(StringUtils.hasText(dto.getType()), Msg::getType, dto.getType())
                .like(StringUtils.hasText(dto.getTitle()), Msg::getTitle, dto.getTitle())
                .eq(dto.getReadStatus() != null, Msg::getReadStatus, dto.getReadStatus())
                .eq(dto.getPriority() != null, Msg::getPriority, dto.getPriority())
                .ge(dto.getStartTime() != null, Msg::getSendTime, dto.getStartTime())
                .le(dto.getEndTime() != null, Msg::getSendTime, dto.getEndTime())
                .orderByDesc(Msg::getSendTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));

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
