package org.wms.common.enums.stock;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AlertStatusEnums {
    NORMAL(0, "正常"),
    LOW(1, "低于最小库存"),
    HIGH(2, "高于最大库存");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    AlertStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
