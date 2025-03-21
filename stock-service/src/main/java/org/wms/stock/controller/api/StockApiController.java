package org.wms.stock.controller.api;

import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.stock.Stock;
import org.wms.stock.service.StockService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/stock")
public class StockApiController {

    @Resource
    StockService stockService;

    /**
     * 根据产品编码和批次号检查库存
     *
     * @param code        产品编码
     * @param batchNumber 批次号
     * @return 库存信息
     */
    @GetMapping("/checkStockByCodeAndBatch")
    public Stock checkStockByCodeAndBatch(@RequestParam("code") String code,
                                          @RequestParam("batchNumber") String batchNumber) {
        return stockService.lambdaQuery().eq(Stock::getProductCode, code)
                .eq(Stock::getBatchNumber, batchNumber).one();
    }

    /**
     * 更新库存
     *
     * @param stock 库存信息
     * @return boolean
     */
    @PutMapping("/update")
    public boolean updateStock(@RequestBody Stock stock) {
        return stockService.updateById(stock);
    }

    @PostMapping("/add")
    public boolean addStock(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

}
