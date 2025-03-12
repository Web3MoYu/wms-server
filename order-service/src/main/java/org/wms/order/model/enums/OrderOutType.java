package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderOutType {
    SALES(1, "销售出库"),
    TRANSFER(2, "调拨出库"),
    OTHER(3, "其他出库");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    OrderOutType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
