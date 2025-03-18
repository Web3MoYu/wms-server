package org.wms.order.controller;

import java.util.List;
import java.util.Objects;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.enums.order.OrderType;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;
import org.wms.security.util.SecurityUtil;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @Resource
    OrderInService orderInService;

    @Resource
    OrderOutService orderOutService;

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
     * 查询入库订单详情
     *
     * @param id 订单ID
     * @return 入库订单详情
     */
    @GetMapping("/inDetail")
    @PreAuthorize("isAuthenticated()")
    public Result<List<OrderDetailVo<OrderInItem>>> inDetail(@RequestParam("id") String id) {
        return orderService.inDetail(id);
    }

    /**
     * 查询出库订单详情
     *
     * @param id 订单ID
     * @return 出库订单详情
     */
    @GetMapping("/outDetail")
    @PreAuthorize("isAuthenticated()")
    public Result<List<OrderDetailVo<OrderOutItem>>> outDetail(@RequestParam("id") String id) {
        return orderService.outDetail(id);
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

    /**
     * 取消订单
     *
     * @param id     订单ID
     * @param type   订单类型
     * @param remark 备注
     * @return 取消结果
     */
    @DeleteMapping("/cancel/{type}/{id}")
    @PreAuthorize("hasAuthority('order:in-out:cancel')")
    @GlobalTransactional
    public Result<String> cancel(@PathVariable("id") String id, @PathVariable Integer type, @RequestParam("remark") String remark) {
        String dbId = null;
        String userId = SecurityUtil.getUserID();
        if (Objects.equals(type, OrderType.IN_ORDER.getCode())) {
            dbId = orderInService.getById(id).getCreator();
        } else {
            dbId = orderOutService.getById(id).getCreator();
        }
        if (Objects.isNull(dbId) || !dbId.equals(userId)) {
            return Result.error(402, "权限不足");
        }
        return orderService.updateStatus(type, id, remark, OrderStatusEnums.CANCELED);
    }
}
