package org.wms.msg.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum NoticeStatus {
    UN_PUBLISHED(0, "未发布"),
    PUBLISHED(1, "已发布"),
    DEPRECATED(2, "已废弃");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    NoticeStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
