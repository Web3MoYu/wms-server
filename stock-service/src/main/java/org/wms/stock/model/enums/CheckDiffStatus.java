package org.wms.stock.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CheckDiffStatus {

    NO(0, "无"),
    YES(1, "有");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    CheckDiffStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
