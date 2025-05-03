package org.wms.stock.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.stock.Alert;
import org.wms.stock.service.AlertService;

@RestController
@RequestMapping("/api/stock/alert")
public class AlertApiController {

    @Resource
    AlertService alertService;


    /**
     * 添加预警信息
     *
     * @param alert 预警信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public boolean addAlert(@RequestBody Alert alert) {
        return alertService.save(alert);
    }
}
