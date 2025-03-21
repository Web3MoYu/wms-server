package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wms.common.entity.product.Product;

@FeignClient(value = "order-service", contextId = "order")
public interface OrderClient {
    /**
     * 更新产品信息
     * 
     * @param product 产品信息
     * @return 是否成功
     */
    @PutMapping("/api/order/product")
    boolean updateProduct(@RequestBody Product product);
}
