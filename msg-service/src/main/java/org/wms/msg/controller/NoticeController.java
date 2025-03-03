package org.wms.msg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.msg.model.dto.NoticePageDTO;
import org.wms.msg.model.vo.NoticeVO;
import org.wms.msg.service.NoticeService;

@RestController
@RequestMapping("/msg")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @PostMapping("/pageList")
    @PreAuthorize("hasAuthority('sys:notice:index')")
    public Result<Page<NoticeVO>> pageList(@RequestBody NoticePageDTO param) {
        return noticeService.pageList(param);
    }
}
