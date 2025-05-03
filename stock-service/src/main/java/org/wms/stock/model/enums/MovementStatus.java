package org.wms.stock.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MovementStatus {

    REJECT(-1, "拒绝"),
    PENDING(0, "未审批"),
    TO_BE_CHANGED(1, "待变动"),
    COMPLETED(2, "已完成");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MovementStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
