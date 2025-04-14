package org.wms.location.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.location.Shelf;

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