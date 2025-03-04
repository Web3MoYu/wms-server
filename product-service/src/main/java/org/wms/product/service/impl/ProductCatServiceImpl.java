package org.wms.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.service.ProductCatService;
import org.wms.product.mapper.ProductCatMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【product_cat(产品分类表)】的数据库操作Service实现
* @createDate 2025-03-04 21:34:08
*/
@Service
public class ProductCatServiceImpl extends ServiceImpl<ProductCatMapper, ProductCat>
    implements ProductCatService{

}




