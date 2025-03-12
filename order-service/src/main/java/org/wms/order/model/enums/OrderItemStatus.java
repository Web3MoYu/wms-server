package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderItemStatus {
    NOT_STARTED(0, "待开始"),
    PARTIALLY_COMPLETED(1, "部分完成"),
    COMPLETED(2, "已完成");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    OrderItemStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
