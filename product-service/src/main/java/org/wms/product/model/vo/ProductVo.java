package org.wms.product.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.product.model.entity.Product;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductVo extends Product {
    String categoryName;
}
