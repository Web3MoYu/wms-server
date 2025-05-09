package org.wms.product.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.common.entity.product.Product;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductVo extends Product {
    String categoryName;
}
