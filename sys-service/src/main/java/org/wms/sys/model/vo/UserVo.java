package org.wms.sys.model.vo;

import lombok.Data;
import org.wms.common.entity.sys.User;

@Data
public class UserVo extends User {
    private String roleId;

    private boolean resetPassword;
}
