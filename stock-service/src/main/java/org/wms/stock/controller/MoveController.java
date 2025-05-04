package org.wms.stock.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.AddMovementDto;
import org.wms.stock.model.dto.MovementDto;
import org.wms.stock.model.vo.MovementVo;
import org.wms.stock.service.MovementService;

@RestController
@RequestMapping("/stock/move")
public class MoveController {

    @Resource
    MovementService movementService;

    /**
     * 分页查询库位移动信息
     *
     * @param dto 查询条件
     * @return 分页数据
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('inventory:move')")
    public Result<Page<MovementVo>> pageMovement(@RequestBody MovementDto dto) {
        return Result.success(movementService.pageMovement(dto), "查询成功");
    }

    /**
     * 新增库存移动
     *
     * @param dto 库位移动信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public Result<String> addMovement(@RequestBody AddMovementDto dto) {
        return Result.success(null, movementService.addMovement(dto));
    }

    /**
     * 审批通过
     *
     * @param id 变更ID
     * @return 是否成功
     */
    @GetMapping("/approve/{id}")
    public Result<String> approveMovement(@PathVariable String id) {
        return Result.success(null, movementService.approveMovement(id));
    }

    /**
     * 审批拒绝
     *
     * @param id     变更ID
     * @param reason 原因
     * @return 是否成功
     */
    @GetMapping("/reject/{id}")
    public Result<String> rejectMovement(@PathVariable String id, @RequestParam("reason") String reason) {
        return Result.success(null, movementService.rejectMovement(id, reason));
    }

    /**
     * 执行变更
     *
     * @param id 变更ID
     * @return 执行结果
     */
    @GetMapping("/doneMove/{id}")
    @GlobalTransactional
    public Result<String> doneMovement(@PathVariable String id) {
        return Result.success(null, movementService.doneMovement(id));
    }
}
