package org.wms.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.product.model.entity.Product;
import org.wms.product.service.ProductService;
import org.wms.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【product(产品表)】的数据库操作Service实现
* @createDate 2025-03-04 21:33:40
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




