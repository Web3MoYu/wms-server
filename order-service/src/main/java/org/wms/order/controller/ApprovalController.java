package org.wms.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderService;

@RestController
@RequestMapping("/order/approval")
public class ApprovalController {

    @Resource
    OrderService orderService;

    /**
     * 分页查询审批订单信息
     *
     * @param queryDto 查询条件s
     * @return 订单分页数据
     */
    @PostMapping("/pageOrder")
    @PreAuthorize("hasAuthority('order:in-out:approval')")
    public Result<Page<OrderVo>> pageOrder(@RequestBody OrderQueryDto queryDto) {
        queryDto.setStatus(OrderStatusEnums.PENDING_REVIEW.getCode());
        return orderService.pageOrder(queryDto);
    }
}
