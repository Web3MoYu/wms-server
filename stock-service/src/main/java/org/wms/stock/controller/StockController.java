package org.wms.stock.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.stock.service.StockService;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    StockService stockService;

}
