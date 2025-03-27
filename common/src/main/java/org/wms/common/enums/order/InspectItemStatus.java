package org.wms.common.enums.order;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum InspectItemStatus {
    QUALIFIED(1, "合格"),
    UNQUALIFIED(2, "不合格");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    InspectItemStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
