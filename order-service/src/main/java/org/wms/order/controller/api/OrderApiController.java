package org.wms.order.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.product.Product;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.service.OrderInItemService;
import org.wms.order.service.OrderOutItemService;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Resource
    OrderInItemService orderInItemService;

    @Resource
    OrderOutItemService orderOutItemService;

    @PutMapping("/product")
    public boolean updateProduct(@RequestBody Product product) {
        // 更新入库订单
        boolean in = orderInItemService.lambdaUpdate()
                .eq(OrderInItem::getProductId, product.getId())
                .set(OrderInItem::getProductName, product.getProductName())
                .set(OrderInItem::getProductCode, product.getProductCode()).update();

        // 更新出库订单
        boolean out = orderOutItemService.lambdaUpdate()
                .eq(OrderOutItem::getProductId, product.getId())
                .set(OrderOutItem::getProductName, product.getProductName())
                .set(OrderOutItem::getProductCode, product.getProductCode()).update();

        return in && out;

    }
}
