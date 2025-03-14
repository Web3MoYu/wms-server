package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderStatusEnums {
    PENDING_REVIEW(0, "待审核"),
    APPROVED(1, "已审核"),
    PARTIALLY_COMPLETED(2, "部分完成"),
    COMPLETED(3, "已完成"),
    CANCELED(-1, "已取消");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    OrderStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
