package org.wms.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.vo.OrderVo;

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
}
