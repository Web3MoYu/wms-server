package org.wms.location.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.wms.location.model.enums.LocationStatusEnums;

/**
 * 库位表
 *
 * @TableName wms_storage_location
 */
@TableName(value = "wms_storage_location")
@Data
public class Storage {
    /**
     * 库位ID
     */
    @TableId
    private String id;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 货架ID
     */
    private String shelfId;

    /**
     * 库位编码
     */
    private String locationCode;

    /**
     * 库位名称
     */
    private String locationName;

    /**
     * 状态：0-占用，1-空闲，2-禁用
     */
    private LocationStatusEnums status;

    /**
     * 当前存放的产品ID
     */
    private String productId;

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