package org.wms.sys.Vo;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleVo {

    private String menuId;

    private String parentId;

    private List<Long> permissions;
}
