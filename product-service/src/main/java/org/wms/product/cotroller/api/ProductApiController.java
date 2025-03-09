package org.wms.product.cotroller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.Product;
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
        return productService.getById(id);
    }
}