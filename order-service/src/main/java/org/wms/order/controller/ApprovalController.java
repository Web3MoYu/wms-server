package org.wms.order.controller;

import java.util.List;
import java.util.Objects;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.enums.order.OrderType;
import org.wms.common.model.Result;
import org.wms.order.model.dto.ApprovalDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderInService;
import org.wms.order.service.OrderOutService;
import org.wms.order.service.OrderService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/order/approval")
public class ApprovalController {

    @Resource
    OrderService orderService;

    @Resource
    OrderInService orderInService;

    @Resource
    OrderOutService orderOutService;

    /**
     * 分页查询审批订单信息
     *
     * @param queryDto 查询条件s
     * @return 订单分页数据
     */
    @PostMapping("/pageOrder")
    @PreAuthorize("hasAuthority('order:in-out:approval')")
    public Result<Page<OrderVo>> pageOrder(@RequestBody OrderQueryDto queryDto) {
        return orderService.pageOrder(queryDto);
    }

    /**
     * 驳回订单
     *
     * @param id     订单ID
     * @param type   订单类型
     * @param remark 备注
     * @return 驳回结果
     */
    @PutMapping("/reject/{type}/{id}")
    @PreAuthorize("hasAuthority('order:in-out:approval')")
    @GlobalTransactional
    public Result<String> reject(@PathVariable("id") String id, @PathVariable Integer type,
                                 @RequestParam("remark") String remark) {
        if (checkAuth(id, type)) {
            return Result.error(402, "权限不足");
        }
        return orderService.updateStatus(type, id, remark, OrderStatusEnums.REJECT);
    }

    /**
     * 审批入库订单
     *
     * @param dto       审批信息
     * @param id        订单ID
     * @param inspector 质检人员
     * @return 审批结果
     */
    @PostMapping("/in/{id}/{inspector}")
    @PreAuthorize("hasAuthority('order:in-out:approval')")
    @GlobalTransactional
    public Result<String> approveInOrder(@RequestBody List<ApprovalDto> dto, @PathVariable String id, @PathVariable String inspector) {
        if (checkAuth(id, OrderType.IN_ORDER.getCode())) {
            return Result.error(402, "权限不足");
        }
        return orderService.approvalInBound(id, dto, inspector);
    }


    /**
     * 审批出库订单
     *
     * @param id        订单ID
     * @param inspector 质检人员
     * @return 审批结果
     */
    @PostMapping("/out/{id}/{inspector}")
    @PreAuthorize("hasAuthority('order:in-out:approval')")
    @GlobalTransactional
    public Result<String> approveOutOrder(@PathVariable String id, @PathVariable String inspector) {
        if (checkAuth(id, OrderType.OUT_ORDER.getCode())) {
            return Result.error(402, "权限不足");
        }
        return orderService.approvalOutBound(id, inspector);
    }

    /**
     * 检查权限
     *
     * @param id   订单ID
     * @param type 订单类型
     * @return 是否有权限
     */
    private boolean checkAuth(String id, Integer type) {
        String dbId = null;
        String userID = SecurityUtil.getUserID();
        if (Objects.equals(type, OrderType.IN_ORDER.getCode())) {
            dbId = orderInService.getById(id).getApprover();
        } else {
            dbId = orderOutService.getById(id).getApprover();
        }
        return Objects.isNull(dbId) || !dbId.equals(userID);
    }
}
