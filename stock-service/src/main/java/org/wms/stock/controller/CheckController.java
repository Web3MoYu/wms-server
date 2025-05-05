package org.wms.stock.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.AddCheckDto;
import org.wms.stock.model.dto.CheckQueryDto;
import org.wms.stock.model.vo.CheckItemVo;
import org.wms.stock.model.vo.CheckVo;
import org.wms.stock.service.CheckService;

import java.util.List;

@RestController
@RequestMapping("/stock/check")
public class CheckController {

    @Resource
    CheckService checkService;


    /**
     * 分页查询库存盘点信息
     *
     * @param dto 查询条件
     * @return 分页数据
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('inventory:check')")
    public Result<Page<CheckVo>> page(@RequestBody CheckQueryDto dto) {
        return Result.success(checkService.pageList(dto), "查询成功");
    }

    /**
     * 新增盘点信息
     *
     * @param dto 盘点信息
     * @return 是否成功
     */
    @PostMapping("/add")
    @GlobalTransactional
    public Result<String> add(@RequestBody AddCheckDto dto) {
        return Result.success(null, checkService.addCheck(dto));
    }

    /**
     * 查看盘点详情
     *
     * @param id 盘点ID
     * @return 详情信息
     */
    @GetMapping("/detail/{id}")
    public Result<List<CheckItemVo>> detailMovement(@PathVariable String id) {
        return Result.success(checkService.detail(id), "查询详情成功");
    }
}
