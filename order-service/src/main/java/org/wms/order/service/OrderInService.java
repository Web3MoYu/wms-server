package org.wms.order.service;

import java.util.List;

import org.wms.common.model.Result;
import org.wms.order.model.dto.ApprovalDto;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【order_in(入库订单表)】的数据库操作Service
 * @createDate 2025-03-12 20:41:27
 */
public interface OrderInService extends IService<OrderIn> {

    /**
     * 添加入库订单
     *
     * @param order 入库订单
     * @return 结果
     */
    Result<String> addOrder(OrderDto<OrderIn, OrderInItem> order);

    /**
     * 查询入库订单详情
     *
     * @param id 入库订单ID
     * @return 结果
     */
    Result<List<OrderDetailVo<OrderInItem>>> inDetail(String id);

    /**
     * 更新订单状态
     *
     * @param type        订单类型
     * @param id          订单ID
     * @param remark      备注
     * @param statusEnums 状态
     */
    void updateStatus(String id, String remark, OrderStatusEnums statusEnums);

    /**
     * 审批入库订单
     *
     * @param id        入库订单ID
     * @param dto       审批信息
     * @param inspector 质检人员
     * @return 结果
     */
    Result<String> approve(String id, List<ApprovalDto> dto, String inspector);
}
