package org.wms.order.controller;

import java.util.List;
import java.util.Objects;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.assertj.core.util.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.enums.order.OrderType;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.*;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.OrderStatisticsVo;
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
     * 获取出库订单信息
     *
     * @param id 订单ID
     * @return OrderOut
     */
    @GetMapping("/outOrder")
    @PreAuthorize("isAuthenticated()")
    public Result<OrderOut> outOrder(@RequestParam("id") String id) {
        OrderOut one = orderOutService.lambdaQuery().eq(OrderOut::getId, id).one();
        return Result.success(one, "获取出库订单信息成功");
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
     * 入库的收货
     *
     * @param id 订单id
     * @return 收货结果
     */
    @PutMapping("/receive/{type}/{id}")
    @PreAuthorize("isAuthenticated()")
    @GlobalTransactional
    public Result<String> receiveGoods(@PathVariable String id, @PathVariable Integer type) {
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
        return orderService.receiveGoods(id);
    }

    /**
     * 添加出库订单
     *
     * @param order 订单
     * @return 添加结果
     */
    @PostMapping("/addOrderOut")
    @PreAuthorize("hasAuthority('order:in-out:add')")
    @GlobalTransactional
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

    /**
     * 出库订单id
     *
     * @param id 出库订单id
     * @return 是否出库成功
     */
    @PutMapping("/doneOutBound/{id}")
    @GlobalTransactional
    public Result<String> doneOutBound(@PathVariable String id) {
        return orderOutService.doneOutBound(id);
    }

    /**
     * 统计订单数量
     *
     * @return 只有两个大小的数组，第一个是入库订单数量，第二个是出库订单数量
     */
    @GetMapping("/getOrderCount")
    public Result<List<Long>> getOrderCount() {
        List<Long> res = Lists.newArrayList();
        long orderInCount = orderInService.count();
        long orderOutCount = orderOutService.count();
        res.add(orderInCount);
        res.add(orderOutCount);
        return Result.success(res, "查询成功");
    }

    /**
     * 获取订单统计信息
     *
     * @param type  0-出库，1-入库
     * @param range 时间范围：1day, 1week, 1month, 3months, 6months
     * @return 统计信息
     */
    @GetMapping("/statistics/{type}")
    public Result<List<OrderStatisticsVo>> getOrderStatistics(@PathVariable Integer type, @RequestParam("range") String range) {
        return Result.success(orderService.getOrderStatistics(type, range), "查询成功");
    }
}
