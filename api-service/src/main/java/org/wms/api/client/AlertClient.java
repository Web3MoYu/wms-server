package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wms.common.entity.stock.Alert;

@FeignClient(value = "stock-service", contextId = "alert")
public interface AlertClient {

    /**
     * 添加预警信息
     *
     * @param alert 预警信息
     * @return 是否成功
     */
    @PostMapping("/api/stock/alert/add")
    boolean addAlert(@RequestBody Alert alert);
}
