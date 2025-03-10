package org.wms.stock.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.stock.model.entity.Stock;
import org.wms.stock.service.StockService;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    StockService stockService;

    @GetMapping("/t")
    public Stock getStock() {
        return stockService.getById(1);
    }
}
