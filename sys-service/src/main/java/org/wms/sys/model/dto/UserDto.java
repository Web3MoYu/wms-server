package org.wms.sys.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {

    private String roleId;
    private String roleName;
}
