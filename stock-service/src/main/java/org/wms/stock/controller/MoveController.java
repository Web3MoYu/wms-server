package org.wms.stock.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
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
}
