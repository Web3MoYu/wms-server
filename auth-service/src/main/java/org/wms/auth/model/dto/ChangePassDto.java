package org.wms.auth.model.dto;

import lombok.Data;

@Data
public class ChangePassDto {

    /**
     * 旧密码
     */
    private String oldPass;
    /**
     * 新密码
     */
    private String newPass;
}
