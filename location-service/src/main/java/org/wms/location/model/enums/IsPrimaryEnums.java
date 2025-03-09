package org.wms.location.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum IsPrimaryEnums {
    NO(0, "否"),
    YES(1, "是");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    IsPrimaryEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
