package org.wms.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.product.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【product(产品表)】的数据库操作Mapper
 * @createDate 2025-03-04 21:33:40
 * @Entity org.wms.product.model.entity.Product
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}




