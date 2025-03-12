package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgTypeEnums {
    STOCK_WARNING(1, "库存预警"),
    QUALITY_CHECK(2, "质检通知"),
    ORDER_STATUS(3, "订单状态"),
    EXCEPTION_ALERT(4, "异常通知"),
    RESTOCK_ALERT(5, "补货通知"),
    OTHER(6, "其他");

    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgTypeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
