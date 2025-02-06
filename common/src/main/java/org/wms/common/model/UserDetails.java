package org.wms.common.model;

import lombok.Data;

import java.util.Set;

@Data
public class UserDetails {
    private String ID;
    private Set<String> authorities;
}
