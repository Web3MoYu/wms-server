package org.wms.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderType {

    IN_ORDER(1, "入库"),
    OUT_ORDER(0, "出库");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    OrderType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
