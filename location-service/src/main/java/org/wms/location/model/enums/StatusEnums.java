package org.wms.location.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusEnums {
    ENABLED(1, "启用"),

    DISABLED(0, "禁用");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    StatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
