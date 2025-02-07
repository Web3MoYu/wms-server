package org.wms.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色菜单关系表的实体类
 */
@Data
@TableName("sys_role_menu")
public class MenuRole {

    @TableId(type = IdType.ASSIGN_ID)
    private String roleMenuId;

    private String menuId;

    private String roleId;
}
