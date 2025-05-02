package org.wms.stock.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.AlertQueryDto;
import org.wms.stock.model.vo.AlertVo;
import org.wms.stock.service.AlertService;

@RestController
@RequestMapping("/stock/alert")
public class AlertController {

    @Resource
    AlertService alertService;

    /**
     * 根据查询条件分页查询预警信息
     *
     * @param dto 查询条件
     * @return 预警的分页信息
     */
    @PostMapping("/pages")
    @PreAuthorize("hasAuthority('inventory:alert:list')")
    public Result<Page<AlertVo>> pages(@RequestBody AlertQueryDto dto) {
        return Result.success(alertService.pageList(dto), "查询成功");
    }
}
