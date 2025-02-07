package org.wms.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色关系表的实体类
 */
@Data
@TableName("sys_user_role")
public class UserRole {

    String userRoleId;

    String userId;

    String roleId;

}
