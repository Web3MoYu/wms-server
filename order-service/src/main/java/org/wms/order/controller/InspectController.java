package org.wms.order.controller;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.order.model.dto.InBoundInspectDto;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.vo.InspectionDetailVo;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.service.InspectionService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
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

    /**
     * 查询质检订单详情
     *
     * @param id 质检ID
     * @return 质检订单详情
     */
    @GetMapping("/inDetail")
    @PreAuthorize("isAuthenticated()")
    public Result<InspectionDetailVo<OrderInItem>> inDetail(@RequestParam("id") String id) {
        return inspectService.inDetail(id);
    }

    /**
     * 入库质检
     *
     * @param dto 质检参数
     * @return 质检结果
     */
    @PostMapping("/inBoundCheck")
    @PreAuthorize("isAuthenticated()")
    @GlobalTransactional
    public Result<String> inBoundCheck(@RequestBody InBoundInspectDto dto) {
        // 检查有无权限
        Inspection one = inspectService.lambdaQuery().eq(Inspection::getInspectionNo, dto.getInspectionNo()).one();
        if (StrUtil.equals(one.getInspectionNo(), SecurityUtil.getUserID())) {
            throw new BizException(303, "无操作权限");
        }
        return inspectService.inBoundCheck(dto);
    }
}
