package org.wms.stock.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.CheckQueryDto;
import org.wms.stock.model.vo.CheckVo;
import org.wms.stock.service.CheckService;

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
}
