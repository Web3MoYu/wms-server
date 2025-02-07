package org.wms.common.model;

import lombok.Data;

import java.util.Set;

/**
 * 封装用户信息
 */
@Data
public class UserDetails {
    private String ID;
    private Set<String> authorities;
}
