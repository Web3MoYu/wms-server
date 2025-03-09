package org.wms.location.model.dto;

import java.util.List;

import org.wms.common.entity.User;
import org.wms.location.model.entity.Area;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AreaDto extends Area {

    // 主要质检员
    private User primaryUser;

    // 次要质检员
    private List<User> secondaryUsers;
}
