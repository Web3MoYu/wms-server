package org.wms.common.entity.location;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 货架表
 *
 * @TableName wms_shelf
 */
@TableName(value = "wms_shelf")
@Data
public class Shelf {
    /**
     * 货架ID
     */
    @TableId
    private String id;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 货架名称
     */
    private String shelfName;

    /**
     * 货架编码
     */
    private String shelfCode;

    /**
     * 状态：0-禁用，1-启用
     */
    private StatusEnums status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updateTime;

}