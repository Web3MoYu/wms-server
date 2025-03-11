package org.wms.common.entity.product;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 产品表
 *
 * @TableName product
 */
@TableName(value = "product")
@Data
public class Product {
    /**
     * 产品ID
     */
    @TableId
    private String id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 规格
     */
    private String spec;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 最小库存（预警阈值）
     */
    private Integer minStock;

    /**
     * 最大库存
     */
    private Integer maxStock;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}