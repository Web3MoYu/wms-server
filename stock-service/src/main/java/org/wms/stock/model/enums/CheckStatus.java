package org.wms.stock.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CheckStatus {

    WAIT_CHECK(0, "待盘点"),
    WAIT_CONFIRM(1, "待确认"),
    COMPLETED(2, "已完成");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    CheckStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
