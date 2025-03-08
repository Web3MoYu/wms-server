package org.wms.location.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.location.model.entity.Area;

/**
 * 区域信息VO，包含负责人名称
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AreaVo extends Area {
    
    /**
     * 区域负责人名称
     */
    private String areaManagerName;
} 