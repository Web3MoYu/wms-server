package org.wms.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import org.wms.order.service.PickingOrderService;

import java.util.List;

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

    /**
     * 批量增加拣货信息
     *
     * @param ids 出库订单ID列表
     * @return 是否增加成功。
     */
    @PostMapping("/add/{picker}")
    @GlobalTransactional
    public Result<String> batchAddPickings(@RequestBody List<String> ids, @PathVariable String picker) {
        return pickingOrderService.batchAddPickings(ids, picker);
    }
}
