package org.wms.order.controller;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.order.model.dto.InBoundInspectDto;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.dto.StockInDto;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.vo.InspectStatisticsVo;
import org.wms.order.model.vo.InspectionDetailVo;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.service.InspectionService;
import org.wms.security.util.SecurityUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/order/inspect")
public class InspectController {

    @Resource
    InspectionService inspectService;

    /**
     * 查询入库订单列表
     *
     * @param dto 查询条件
     * @return 结果
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('order:inspect:list')")
    public Result<Page<InspectionVo>> pageList(@RequestBody InspectionDto dto) {
        return Result.success(inspectService.pageList(dto), "查询成功");
    }

    /**
     * 查询质检订单详情
     *
     * @param id 入库质检ID
     * @return 质检订单详情
     */
    @GetMapping("/inDetail")
    @PreAuthorize("isAuthenticated()")
    public Result<InspectionDetailVo<OrderInItem>> inDetail(@RequestParam("id") String id) {
        return inspectService.inDetail(id);
    }


    /**
     * 查询质检订单详情
     *
     * @param id 出库质检ID
     * @return 质检订单详情
     */
    @GetMapping("/outDetail")
    @PreAuthorize("isAuthenticated()")
    public Result<InspectionDetailVo<OrderOutItem>> outDetail(@RequestParam("id") String id) {
        return inspectService.outDetail(id);
    }

    /**
     * 质检
     *
     * @param dto 质检参数
     * @return 质检结果
     */
    @PostMapping("/inBoundCheck")
    @PreAuthorize("isAuthenticated()")
    @GlobalTransactional(timeoutMills = 1000 * 60 * 10 * 100)
    public Result<String> inBoundCheck(@RequestBody InBoundInspectDto dto) {
        // 检查有无权限
        Inspection one = inspectService.lambdaQuery().eq(Inspection::getInspectionNo, dto.getInspectionNo()).one();
        if (StrUtil.equals(one.getInspectionNo(), SecurityUtil.getUserID())) {
            throw new BizException(303, "无操作权限");
        }
        return inspectService.inBoundCheck(dto);
    }


    /**
     * 确认上架
     *
     * @param inspectNo 质检编号
     * @return 上架结果
     */
    @PutMapping("/stockAll/{inspectNo}")
    @PreAuthorize("isAuthenticated()")
    @GlobalTransactional(timeoutMills = 1000 * 60 * 10)
    public Result<String> stockAll(@PathVariable String inspectNo) {
        return inspectService.stockAll(inspectNo);
    }

    /**
     * 上架单个商品
     *
     * @param dto 商品信息
     * @return 上架结果
     */
    @PutMapping("/stockOne")
    @PreAuthorize("isAuthenticated()")
    @GlobalTransactional
    public Result<String> stockOne(@RequestBody StockInDto dto) {
        return inspectService.stockOne(dto);
    }

    /**
     * 获取质检统计信息
     *
     * @param type  all-全部，in-入库，out-出库
     * @param range 时间范围：1day, 1week, 1month, 3months, 6months
     * @return 统计信息
     */
    @GetMapping("/statistics/{type}")
    public Result<List<InspectStatisticsVo>> getInspectionStatistics(@RequestParam("range") String range, @PathVariable String type) {
        return Result.success(inspectService.getInspectionStatistics(type, range), "查询成功");
    }
}
