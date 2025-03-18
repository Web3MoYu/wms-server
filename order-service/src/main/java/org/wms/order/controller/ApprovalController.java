package org.wms.order.controller;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.enums.order.OrderType;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderInService;
import org.wms.order.service.OrderOutService;
import org.wms.order.service.OrderService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;
import org.wms.security.util.SecurityUtil;

import java.util.Objects;

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
    public Result<String> reject(@PathVariable("id") String id, @PathVariable Integer type, @RequestParam("remark") String remark) {
        String dbId = null;
        String userID = SecurityUtil.getUserID();
        if (Objects.equals(type, OrderType.IN_ORDER.getCode())) {
            dbId = orderInService.getById(id).getApprover();
        } else {
            dbId = orderOutService.getById(id).getApprover();
        }
        if (Objects.isNull(dbId) || !dbId.equals(userID)) {
            return Result.error(402,"权限不足");
        }
        return orderService.updateStatus(type, id, remark, OrderStatusEnums.REJECT);
    }
}
