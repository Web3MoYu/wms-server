package org.wms.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的菜单上下级关系
 */
@Data
public class MenuTree implements Serializable {
    /**
     * 当前菜单
     */
    private Menu menu;
    /**
     * 子菜单
     */
    private List<MenuTree> children;
}
