package org.wms.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    /**
     * 分页查询订单信息
     *
     * @param queryDto 查询条件s
     * @return 订单分页数据
     */
    @PostMapping("/pageOrder")
    @PreAuthorize("hasAuthority('order:in-out:list')")
    public Result<Page<OrderVo>> pageOrder(@RequestBody OrderQueryDto queryDto) {
        return orderService.pageOrder(queryDto);
    }

    /**
     * 添加入库订单
     *
     * @param order 订单
     * @return 添加结果
     */
    @PostMapping("/addOrderIn")
    @PreAuthorize("hasAuthority('order:in-out:add')")
    @GlobalTransactional
    public Result<String> addOrderIn(@RequestBody OrderDto<OrderIn, OrderInItem> order) {
        return orderService.addOrderIn(order);
    }

    /**
     * 添加出库订单
     *
     * @param order 订单
     * @return 添加结果
     */
    @PostMapping("/addOrderOut")
    @PreAuthorize("hasAuthority('order:in-out:add')")
    public Result<String> addOrderOut(@RequestBody OrderDto<OrderOut, OrderOutItem> order) {
        return orderService.addOrderOut(order);
    }
}
