package org.wms.msg.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum NoticeIsTop {
    NO(0, "否"), YES(1, "是");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    NoticeIsTop(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
