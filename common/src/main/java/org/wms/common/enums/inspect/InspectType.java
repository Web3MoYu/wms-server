package org.wms.common.enums.inspect;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum InspectType {
    INBOUND_INSPECT(1, "入库质检"),
    OUTBOUND_INSPECT(2, "出库质检"),
    STOCK_INSPECT(3, "库存质检");
    @JsonValue
    @EnumValue
    private final Integer code;
    private final String desc;

    InspectType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
