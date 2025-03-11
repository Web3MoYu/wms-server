package org.wms.common.model.vo;

import lombok.Data;

import java.util.Set;

@Data
public class LocationVo {

    // 货架名称
    private String shelfName;

    // 库位名称
    private Set<String> storageNames;
}
