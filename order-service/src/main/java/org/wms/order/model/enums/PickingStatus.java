package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PickingStatus {
    UNPICKING(0, "为拣货"),
    PICKING(1, "拣货中"),
    PICKED(2, "已完成"),
    ERROR(3, "拣货异常");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    PickingStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
