package org.wms.product.model.vo;

import lombok.Data;
import org.wms.product.model.entity.ProductCat;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的产品类别上下级关系
 */
@Data
public class ProductCatTree implements Serializable {
    /**
     * 当前类别
     */
    private ProductCat category;

    /**
     * 子类别
     */
    private List<ProductCatTree> children;
} 