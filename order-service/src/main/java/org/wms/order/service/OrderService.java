package org.wms.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.vo.OrderVo;

public interface OrderService {
    /**
     * 分页查询订单信息
     *
     * @param queryDto 查询条件s
     * @return 订单分页数据
     */
    Result<Page<OrderVo>> pageOrder(OrderQueryDto queryDto);
}
