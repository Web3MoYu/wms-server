package org.wms.auth.model.dto;

import lombok.Data;

@Data
public class ChangePassDto {
    private String oldPass;
    private String newPass;
}
