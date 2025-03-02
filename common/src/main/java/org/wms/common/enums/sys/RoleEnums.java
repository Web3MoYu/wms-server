package org.wms.common.enums.sys;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleEnums {
    SUPER_ADMIN(0, "超级管理员"), ADMIN(1, "管理员"), EMPLOYEE(2, "员工");

    @EnumValue
    @JsonValue
    private final Integer code;
    private final String desc;

    RoleEnums(Integer type, String desc) {
        this.code = type;
        this.desc = desc;
    }

}
