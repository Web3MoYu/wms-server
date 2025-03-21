package org.wms.order.controller.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.product.Product;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.service.OrderInItemService;
import org.wms.order.service.OrderOutItemService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Resource
    OrderInItemService orderInItemService;

    @Resource
    OrderOutItemService orderOutItemService;

    /**
     * 更新产品信息
     *
     * @param product 产品信息
     * @return 是否成功
     */
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
