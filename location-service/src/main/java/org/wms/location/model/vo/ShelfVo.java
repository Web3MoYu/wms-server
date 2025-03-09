package org.wms.location.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.location.model.entity.Shelf;

/**
 * 货架信息VO，包含区域名称
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShelfVo extends Shelf {

    /**
     * 区域名称
     */
    private String areaName;
} 