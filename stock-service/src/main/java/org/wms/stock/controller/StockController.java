package org.wms.stock.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.StockDto;
import org.wms.stock.model.vo.StockVo;
import org.wms.stock.service.StockService;

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

}
