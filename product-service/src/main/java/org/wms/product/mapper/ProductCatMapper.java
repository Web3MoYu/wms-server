package org.wms.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.product.model.entity.ProductCat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【product_cat(产品分类表)】的数据库操作Mapper
 * @createDate 2025-03-04 21:34:08
 * @Entity org.wms.product.model.entity.ProductCat
 */
@Mapper
public interface ProductCatMapper extends BaseMapper<ProductCat> {

}




