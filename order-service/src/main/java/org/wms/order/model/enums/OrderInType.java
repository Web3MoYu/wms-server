package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderInType {
    PURCHASE(1, "采购入库"),
    AUTO(2, "自动入库");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    OrderInType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
