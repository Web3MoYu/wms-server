package org.wms.msg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.msg.model.dto.NoticePageDTO;
import org.wms.msg.model.entity.Notice;
import org.wms.msg.model.enums.NoticeStatus;
import org.wms.msg.model.vo.NoticeVO;
import org.wms.msg.service.NoticeService;
import org.wms.security.util.SecurityUtil;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/msg/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @PostMapping("/pageList")
    @PreAuthorize("hasAuthority('sys:notice:list')")
    public Result<Page<NoticeVO>> pageList(@RequestBody NoticePageDTO param) {
        return noticeService.pageList(param);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('sys:notice:update')")
    public Result<String> update(@PathVariable String id, @RequestBody Notice notice) {
        notice.setUpdateTime(LocalDateTime.now());
        boolean update = noticeService.lambdaUpdate().eq(Notice::getId, id).update(notice);
        if (update) {
            return Result.success(null, "修改成功");
        }
        return Result.error(500, "修改失败");
    }

    @DeleteMapping("/abandon/{id}")
    @PreAuthorize("hasAuthority('sys:notice:delete')")
    public Result<String> abandoned(@PathVariable String id) {

        boolean remove = noticeService.lambdaUpdate()
                .eq(Notice::getId, id)
                .set(Notice::getEndTime, LocalDateTime.now())
                .set(Notice::getStatus, NoticeStatus.DEPRECATED.getCode())
                .update();
        if (remove) {
            return Result.success(null, "废弃成功");
        }
        return Result.error(500, "废弃失败");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:notice:add')")
    public Result<String> add(@RequestBody Notice notice) {
        String userId = SecurityUtil.getUserID();
        notice.setPublisher(userId);
        notice.setCreateTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        boolean save = noticeService.save(notice);
        if (save) {
            return Result.success(null, "添加成功");
        }
        return Result.error(500, "添加失败");
    }

    @PutMapping("/publish/{id}")
    @PreAuthorize("hasAuthority('sys:notice:publish')")
    public Result<String> publish(@PathVariable String id) {
        boolean update = noticeService.lambdaUpdate()
                .eq(Notice::getId, id)
                .set(Notice::getPublishTime, LocalDateTime.now())
                .set(Notice::getStatus, NoticeStatus.PUBLISHED.getCode())
                .update();
        if (update) {
            return Result.success(null, "发布成功");
        }
        return Result.error(500, "发布失败");
    }
}
