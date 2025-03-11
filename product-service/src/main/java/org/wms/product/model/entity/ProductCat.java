package org.wms.product.model.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;

/**
 * 产品分类表
 *
 * @TableName product_cat
 */
@TableName(value = "product_cat")
@Data
public class ProductCat {
    /**
     * 分类ID
     */
    @TableId
    private String id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

}