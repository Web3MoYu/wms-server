package org.wms.order.controller.api;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.entity.order.InspectionItem;
import org.wms.order.service.InspectionItemService;

@RestController
@RequestMapping("/api/inspect")
public class InspectApiController {

    @Resource
    InspectionItemService inspectionItemService;

    /**
     * 根据ID获取质检详情信息
     *
     * @param id ID
     * @return InspectionItem
     */
    @GetMapping("/getItemById/{id}")
    public InspectionItem getItemById(@PathVariable("id") String id) {
        return inspectionItemService.getById(id);
    }
}
