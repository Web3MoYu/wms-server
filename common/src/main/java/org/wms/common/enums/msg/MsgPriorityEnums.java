package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgPriorityEnums {

    NORMAL(0, "普通"),
    IMPORTANT(1, "重要"),
    URGENT(2, "紧急");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgPriorityEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
