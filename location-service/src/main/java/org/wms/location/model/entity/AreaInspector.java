package org.wms.location.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.location.model.enums.IsPrimaryEnums;

/**
 * 区域质检员关系表
 *
 * @TableName wms_area_inspector
 */
@TableName(value = "wms_area_inspector")
@Data
public class AreaInspector {
    /**
     * 关系ID
     */
    @TableId
    private String id;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 质检员ID
     */
    private String inspectorId;

    /**
     * 质检员姓名
     */
    private String inspectorName;

    /**
     * 质检员联系电话
     */
    private String inspectorPhone;

    /**
     * 是否主要负责人：0-否，1-是
     */
    private IsPrimaryEnums isPrimary;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime updateTime;

}