package org.wms.common.entity.location;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 区域表
 *
 * @TableName wms_area
 */
@TableName(value = "wms_area")
@Data
public class Area implements Serializable {
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