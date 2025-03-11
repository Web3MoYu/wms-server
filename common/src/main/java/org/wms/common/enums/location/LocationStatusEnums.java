package org.wms.common.enums.location;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LocationStatusEnums {
    OCCUPIED(0, "占用"),
    FREE(1, "空闲"),
    DISABLED(2, "禁用");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    LocationStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
