package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wms.common.entity.product.Product;

@FeignClient(value = "product-service", contextId = "product")
public interface ProductClient {

    /**
     * 根据id获取产品信息
     *
     * @param id 产品id
     * @return 产品信息
     */
    @GetMapping("/api/product/get")
    Product getProductById(@RequestParam String id);

    /**
     * 根据code检查产品是否存在
     *
     * @param code code
     * @return boolean
     */
    @GetMapping("/api/product/get/{code}")
    boolean getProductByCode(@PathVariable String code);

    /**
     * 创建产品
     *
     * @param product 产品信息
     * @return boolean
     */
    @PostMapping("/api/product/add")
    boolean createProduct(@RequestBody Product product);
}
