package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgReadEnums {
    READ(0, "未读"),
    UNREAD(1, "已读");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgReadEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
