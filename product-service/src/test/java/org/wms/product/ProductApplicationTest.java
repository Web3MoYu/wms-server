package org.wms.product;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.wms.product.mapper.ProductCatMapper;
import org.wms.product.mapper.ProductMapper;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.service.ProductCatService;
import org.wms.product.service.ProductService;

@SpringBootTest
public class ProductApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(ProductApplicationTest.class);
    @Resource
    ProductMapper productMapper;

    @Resource
    ProductService productService;

    @Resource
    ProductCatService productCatService;

    @Resource
    ProductCatMapper productCatMapper;

    @Resource
    MybatisPlusInterceptor mybatisPlusInterceptor;

    @Test
    void test() {
        System.out.println(productCatMapper.selectList(new Page<ProductCat>(1, 5), null));
        System.out.println(mybatisPlusInterceptor.getInterceptors());
    }
}
