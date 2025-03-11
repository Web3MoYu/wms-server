package org.wms.common.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.wms.common.enums.sys.RoleEnums;

/**
 * 角色实体类
 */
@Data
@TableName("sys_role")
public class Role {
    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;

    private RoleEnums type;
    /**
     * 备注
     */
    private String remark;
}
