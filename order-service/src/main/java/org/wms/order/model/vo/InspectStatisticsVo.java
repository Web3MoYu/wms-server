package org.wms.order.model.vo;

import lombok.Data;

@Data
public class InspectStatisticsVo {
    Integer count; // 质检单数量
    Integer status; // 质检状态：0-未质检，1-通过，2-不通过，3-部分异常
}
