package org.wms.order.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum QualityStatusEnums {
    NOT_INSPECTED(0, "未质检"),
    PASSED(1, "通过"),
    FAILED(2, "不通过"),
    PARTIALLY_EXCEPTIONAL(3, "部分异常");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    QualityStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
