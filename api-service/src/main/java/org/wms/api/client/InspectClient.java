package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wms.common.entity.order.InspectionItem;

@FeignClient(value = "order-service", contextId = "inspect")
public interface InspectClient {

    @GetMapping("/api/inspect/getItemById/{id}")
    InspectionItem getItemById(@PathVariable("id") String id);
}
