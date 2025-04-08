package org.wms.order.service;

import org.wms.common.model.Result;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.entity.OrderOut;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【order_out(出库订单表)】的数据库操作Service
 * @createDate 2025-03-12 20:41:27
 */
public interface OrderOutService extends IService<OrderOut> {

    /**
     * 查询出库订单详情
     *
     * @param id 出库订单ID
     * @return 结果
     */
    Result<List<OrderDetailVo<OrderOutItem>>> outDetail(String id);

    /**
     * 更新订单状态
     *
     * @param id          订单ID
     * @param remark      备注
     * @param statusEnums 状态
     */
    void updateStatus(String id, String remark, OrderStatusEnums statusEnums);

    /**
     * 新增出库订单
     *
     * @param order 出库订单信息
     * @return Result
     */
    Result<String> addOrder(OrderDto<OrderOut, OrderOutItem> order);

    /**
     * 审批出库订单
     *
     * @param id        订单ID
     * @param inspector 质检人员
     * @return 审批结果
     */
    Result<String> approve(String id, String inspector);
}
