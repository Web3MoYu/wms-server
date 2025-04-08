package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wms.common.entity.product.Product;
import org.wms.common.entity.stock.Stock;
import org.wms.common.model.vo.StockVo;

@FeignClient(value = "stock-service", contextId = "stock")
public interface StockClient {

    /**
     * 根据产品编码和批次号检查库存
     *
     * @param code        产品编码
     * @param batchNumber 批次号
     * @return 库存信息
     */
    @GetMapping("/api/stock/checkStockByCodeAndBatch")
    Stock checkStockByCodeAndBatch(@RequestParam("code") String code, @RequestParam("batchNumber") String batchNumber);

    /**
     * 根据产品id和批次号获取库存信息
     *
     * @param productId   产品id
     * @param batchNumber 批次号
     * @return 库存信息
     */
    @GetMapping("/api/stock/getStockByProductIdAndBatch")
    Stock getStockByProductIdAndBatch(@RequestParam("productId") String productId, @RequestParam("batchNumber") String batchNumber);

    /**
     * 更新库存
     *
     * @param stock 库存信息
     * @return boolean
     */
    @PutMapping("/api/stock/update")
    boolean updateStock(@RequestBody Stock stock);

    /**
     * 添加库存
     *
     * @param stock 库存信息
     * @return boolean
     */
    @PostMapping("/api/stock/add")
    boolean addStock(@RequestBody Stock stock);

    /**
     * 更新产品编码
     *
     * @param product 产品信息
     * @return boolean
     */
    @PutMapping("/api/stock/product")
    boolean updateProductCode(@RequestBody Product product);

    /**
     * 根据批次号和商品id查找库存
     *
     * @param batchNumber 批次号
     * @param productId   商品id
     * @return 库存
     */
    @GetMapping("/api/stock/getStockVo")
    StockVo getStockVo(@RequestParam String batchNumber, @RequestParam String productId);
}
