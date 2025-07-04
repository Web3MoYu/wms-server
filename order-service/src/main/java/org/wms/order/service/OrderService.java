package org.wms.order.service;

import java.util.List;

import org.wms.common.model.Result;
import org.wms.order.model.dto.ApprovalDto;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.OrderStatisticsVo;
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
    Result<String> updateStatus(Integer type, String id, String remark, OrderStatusEnums status);

    /**
     * 审批订单
     *
     * @param inspector 质检人员
     * @param id        订单ID
     * @param dto       审批信息
     * @return 审批结果
     */
    Result<String> approvalInBound(String id, List<ApprovalDto> dto, String inspector);

    /**
     * 入库的收货
     *
     * @param id 订单id
     * @return 收货结果
     */
    Result<String> receiveGoods(String id);

    /**
     * 审批出库订单
     *
     * @param id        订单ID
     * @param inspector 质检人员
     * @return 审批结果
     */
    Result<String> approvalOutBound(String id, String inspector);

    /**
     * 获取订单统计信息
     *
     * @param type  0-出库，1-入库
     * @param range 时间范围：1day, 1week, 1month, 3months, 6months
     * @return 统计信息
     */
    List<OrderStatisticsVo> getOrderStatistics(Integer type, String range);
}
