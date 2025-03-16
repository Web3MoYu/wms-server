package org.wms.product.cotroller.api;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.product.Product;
import org.wms.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Resource
    private ProductService productService;

    /**
     * 根据id获取产品信息
     *
     * @param id 产品id
     * @return 产品信息
     */
    @GetMapping("/get")
    public Product getProductById(@RequestParam String id) {
        if (StrUtil.isBlank(id)) {
            return null;
        }
        return productService.getById(id);
    }

    /**
     * 根据code检查产品是否存在
     *
     * @param code code
     * @return boolean
     */
    @GetMapping("/get/{code}")
    public boolean getProductByCode(@PathVariable String code) {
        return productService.lambdaQuery().eq(Product::getProductCode, code).exists();
    }

    /**
     * 插入商品信息
     */
    @PostMapping("/add")
    public boolean createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}