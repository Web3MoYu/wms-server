package org.wms.order.model.vo;

import lombok.Data;

@Data
public class PickingStatisticsVo {
    Integer status; // 状态类型
    Integer orderCount; // 订单数量
    Integer itemCount; // 商品数量数量
    Integer count; // 拣货单数
}
