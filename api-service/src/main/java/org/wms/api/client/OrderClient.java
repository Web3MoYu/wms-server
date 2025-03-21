package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wms.common.entity.product.Product;

@FeignClient(value = "order-service", contextId = "order")
public interface OrderClient {
    @PutMapping("/api/order/product")
    boolean updateProduct(@RequestBody Product product);
}
