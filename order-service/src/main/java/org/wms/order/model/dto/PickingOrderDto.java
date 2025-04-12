package org.wms.order.model.dto;

import lombok.Data;

@Data
public class PickingOrderDto {
    // 基本分页参数
    private Integer page = 1;
    private Integer pageSize = 10;

    private String pickingNo; // 模糊查询拣货编号
    private String picker; // 精准查找拣货人员
    private Integer status; // 状态：0-待拣货，1-拣货中，2-已完成

    // 排序条件 true-升序 false-降序
    private Boolean totalOrdersAsc;
    private Boolean totalItemsAsc;
    private Boolean totalQuantityAsc;
    private Boolean createTimeAsc;

}
