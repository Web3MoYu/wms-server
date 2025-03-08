package org.wms.location.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.location.model.enums.StatusEnums;
import org.wms.location.model.vo.AreaVo;
import org.wms.location.service.AreaService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/location/area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * 分页查询区域信息，包含负责人名称
     *
     * @param page        页码，默认为1
     * @param pageSize    每页条数，默认为10
     * @param areaName    区域名称（可选，模糊查询）
     * @param areaManager 负责人ID（可选）
     * @param status      状态（可选，0-启用，1-禁用）
     * @return 区域分页结果，包含负责人名称
     */
    @PreAuthorize("hasAuthority('location:area:list')")
    @GetMapping("/page")
    public Result<Page<AreaVo>> pageAreas(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String areaName,
            @RequestParam(required = false) String areaManager,
            @RequestParam(required = false) StatusEnums status) {

        Page<AreaVo> result = areaService.pageAreaVos(page, pageSize, areaName, areaManager, status);
        return Result.success(result, "查询成功");
    }
}
