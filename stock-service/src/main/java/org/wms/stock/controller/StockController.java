package org.wms.stock.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.StockDto;
import org.wms.stock.model.entity.Stock;
import org.wms.stock.model.vo.StockVo;
import org.wms.stock.service.StockService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    StockService stockService;

    /**
     * 分页查询库存
     *
     * @param stockDto 分页条件
     * @return 库存列表
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('inventory:stock:list')")
    public Result<Page<StockVo>> getStockPage(@RequestBody StockDto stockDto) {
        return stockService.pageStocks(stockDto);
    }

    /**
     * 新增库存
     *
     * @param stock 库存信息
     * @return 库存信息
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('inventory:stock:add')")
    @GlobalTransactional
    public Result<String> addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

    /**
     * 修改库存信息
     *
     * @param stock 库存信息
     * @return 库存信息
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('inventory:stock:update')")
    public Result<String> updateStock(@RequestBody Stock stock) {
        return stockService.updateStock(stock);
    }

    /**
     * 模糊查找批次号
     *
     * @param batchNumber
     * @return
     */
    @GetMapping("/getBatchNumber/{batchNumber}")
    @PreAuthorize("isAuthenticated()")
    public Result<Set<String>> getBatchNumber(@PathVariable String batchNumber) {
        Set<String> list = stockService.lambdaQuery()
                .like(Stock::getBatchNumber, batchNumber)
                .list().stream().map(Stock::getBatchNumber).collect(Collectors.toSet());
        return Result.success(list, "查询成功");
    }

    /**
     * 根据批次号和商品id查找库存
     *
     * @param batchNumber 批次号
     * @param productId   商品id
     * @return 库存
     */
    @GetMapping("/getStockByBatchAndProduct")
    @PreAuthorize("isAuthenticated()")
    public Result<StockVo> getStock(@RequestParam String batchNumber, @RequestParam String productId) {
        StockVo stockVo = stockService.getStockByBatchAndProduct(batchNumber, productId);
        return Result.success(stockVo, "查询成功");
    }
}
