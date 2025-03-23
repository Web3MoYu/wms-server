package org.wms.order.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.service.InspectionService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/order/inspect")
public class InspectController {

    @Resource
    InspectionService inspectService;

    /**
     * 查询入库订单列表
     *
     * @param dto 查询条件
     * @return 结果
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('order:inspect:list')")
    public Result<Page<InspectionVo>> pageList(@RequestBody InspectionDto dto) {
        return Result.success(inspectService.pageList(dto), "查询成功");
    }
}
