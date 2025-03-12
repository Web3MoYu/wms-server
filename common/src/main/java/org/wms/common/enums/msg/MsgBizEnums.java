package org.wms.common.enums.msg;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MsgBizEnums {

    INBOUND_ORDER(1, "入库"),
    OUTBOUND_ORDER(2, "出库"),
    QUALITY_CHECK(3, "质检"),
    EXCEPTION_FLAG(4, "异常标记"),
    STOCK_WARNING(5, "库存预警");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    MsgBizEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
