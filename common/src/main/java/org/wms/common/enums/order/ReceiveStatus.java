package org.wms.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ReceiveStatus {
    NOT_STARTED(0, "未开始"),

    DONE(1, "已完成"),

    PENDING(2, "进行中");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    ReceiveStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
