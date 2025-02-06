package org.wms.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuTree implements Serializable {
    private Menu menu;
    private List<MenuTree> children;
}
