package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wms.api.client.fallback.ProductClientFallback;
import org.wms.common.entity.product.Product;

@FeignClient(value = "product-service", contextId = "product", fallbackFactory = ProductClientFallback.class)
public interface ProductClient {

    /**
     * 根据id获取产品信息
     *
     * @param id 产品id
     * @return 产品信息
     */
    @GetMapping("/api/product/get")
    Product getProductById(@RequestParam String id);
}
