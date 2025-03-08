package org.wms.location.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.location.model.enums.StatusEnums;

/**
 * 区域表
 * @TableName wms_area
 */
@TableName(value ="wms_area")
@Data
public class Area {
    /**
     * 区域ID
     */
    @TableId
    private String id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 区域负责人
     */
    private String areaManager;

    /**
     * 状态：0-禁用，1-启用
     */
    private StatusEnums status;

    /**
     * 区域描述
     */
    private String description;

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