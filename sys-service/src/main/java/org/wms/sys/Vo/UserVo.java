package org.wms.sys.Vo;

import lombok.Data;
import org.wms.common.entity.User;

@Data
public class UserVo extends User {
    private String roleId;

    private boolean resetPassword;
}
