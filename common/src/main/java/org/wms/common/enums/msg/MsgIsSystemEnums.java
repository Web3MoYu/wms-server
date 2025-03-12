package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgIsSystemEnums {
    NO(0, "否"),

    YES(1, "是");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgIsSystemEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
