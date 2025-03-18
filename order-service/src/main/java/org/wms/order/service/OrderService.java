package org.wms.order.service;

import java.util.List;

import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.OrderVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface OrderService {

    /**
     * 分页查询订单信息
     *
     * @param queryDto 查询条件s
     * @return 订单分页数据
     */
    Result<Page<OrderVo>> pageOrder(OrderQueryDto queryDto);

    /**
     * 添加入库订单
     *
     * @param order 订单
     * @return 添加结果
     */
    Result<String> addOrderIn(OrderDto<OrderIn, OrderInItem> order);

    /**
     * 添加入库订单
     *
     * @param order 订单
     * @return 添加结果
     */
    Result<String> addOrderOut(OrderDto<OrderOut, OrderOutItem> order);

    /**
     * 查询入库订单详情
     *
     * @param id 订单ID
     * @return 入库订单详情
     */
    Result<List<OrderDetailVo<OrderInItem>>> inDetail(String id);

    /**
     * 查询出库订单详情
     *
     * @param id 订单ID
     * @return 出库订单详情
     */
    Result<List<OrderDetailVo<OrderOutItem>>> outDetail(String id);

    /**
     * 取消订单
     *
     * @param id 订单ID
     * @return 取消结果
     */
    Result<String> cancel(Integer type, String id, String remark);
}
