package org.wms.api.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.wms.api.client.ProductClient;
import org.wms.common.entity.product.Product;

public class ProductClientFallback implements FallbackFactory<ProductClient> {
    private static final Logger log = LoggerFactory.getLogger(ProductClientFallback.class);

    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {

            @Override
            public Product getProductById(String id) {
                log.info("触发getProductById兜底");
                return new Product();
            }
        };
    }
}
