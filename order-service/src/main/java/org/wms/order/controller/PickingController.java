package org.wms.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.order.model.dto.BatchAddPickingDto;
import org.wms.order.model.dto.PickingOneDto;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.vo.*;
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
    public Result<Page<PickingOrderVo>> page(@RequestBody PickingOrderDto dto) {
        return pickingOrderService.pageList(dto);
    }

    /**
     * 批量增加拣货信息
     *
     * @param ids 出库订单ID列表
     * @return 是否增加成功。
     */
    @PostMapping("/add")
    @GlobalTransactional
    public Result<String> batchAddPickings(@RequestBody BatchAddPickingDto dto) {
        return pickingOrderService.batchAddPickings(dto);
    }

    /**
     * 获取拣货单详情信息
     *
     * @param pickingId 拣货id
     * @return 拣货的详细信息
     */
    @GetMapping("/pickingDetail/{pickingId}")
    public Result<List<PickingDetailVo>> getPickingDetail(@PathVariable String pickingId) {
        return pickingOrderService.detail(pickingId);
    }


    /**
     * 根据出库订单 ID 获取出库位置信息
     *
     * @param orderId 出库订单ID
     * @return 位置信息
     */
    @GetMapping("/itemLocation/{orderId}")
    public Result<List<PickingLocation>> getPickingLocation(@PathVariable String orderId) {
        return pickingOrderService.getPickingLocation(orderId);
    }

    /**
     * 分拣
     *
     * @param dto 分拣数据
     * @return 是否分拣成功
     */
    @PostMapping("/pickingOne")
    @GlobalTransactional(timeoutMills = 1000 * 60 * 10000)
    public Result<String> pickingOne(@RequestBody List<PickingOneDto> dto) {
        return pickingOrderService.pickOne(dto);
    }

    /**
     * 获取拣货信息统计信息
     *
     * @param range 时间范围：1day, 1week, 1month, 3months, 6months
     * @return 统计信息
     */
    @GetMapping("/statistics")
    public Result<List<PickingStatisticsVo>> getPickingStatistics(@RequestParam("range") String range) {
        return Result.success(pickingOrderService.getPickingStatistics(range), "查询成功");
    }
}
