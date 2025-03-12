package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgEnums {

    HEART_BEAR(0, "心跳"),
    NOTICE(1, "消息提醒");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
