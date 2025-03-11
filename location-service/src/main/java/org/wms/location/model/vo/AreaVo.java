package org.wms.location.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.location.Area;
import org.wms.location.model.entity.AreaInspector;

import java.util.List;

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

    /**
     * 质检员列表
     */
    private List<AreaInspector> inspectors;
} 