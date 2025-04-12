package org.wms.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import org.wms.order.service.PickingOrderService;

@RestController
@RequestMapping("/order/picking")
public class PickingController {

    @Resource
    PickingOrderService pickingOrderService;

    /**
     * 分页查询拣货列表
     *
     * @param dto 分页条件
     * @return 分页数据
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('order:picking:list')")
    public Result<Page<PickingOrder>> page(@RequestBody PickingOrderDto dto) {
        return pickingOrderService.pageList(dto);
    }
}
