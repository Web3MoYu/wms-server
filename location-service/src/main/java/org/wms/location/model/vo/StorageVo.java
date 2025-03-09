package org.wms.location.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.location.model.entity.Storage;

@Data
@EqualsAndHashCode(callSuper = true)
public class StorageVo extends Storage {

    // 区域名称
    private String areaName;

    // 货架名称
    private String shelfName;

    // 产品名称
    private String productName;
}
