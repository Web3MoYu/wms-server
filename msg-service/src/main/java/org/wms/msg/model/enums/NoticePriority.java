package org.wms.msg.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum NoticePriority {
    NORMAL(0, "普通"),
    IMPORTANT(1, "重要"),
    URGENT(2, "紧急");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    NoticePriority(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
