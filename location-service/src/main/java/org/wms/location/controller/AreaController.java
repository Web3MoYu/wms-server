package org.wms.location.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.location.model.dto.AreaDto;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.location.StatusEnums;
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
            @RequestParam(required = false) Integer status) {

        Page<AreaVo> result = areaService.pageAreaVos(page, pageSize, areaName, areaManager, status);
        return Result.success(result, "查询成功");
    }

    /**
     * 新增区域
     *
     * @param areaDto 区域信息
     * @return 添加结果
     */
    @PreAuthorize("hasAuthority('location:area:add')")
    @PostMapping
    public Result<String> addArea(@RequestBody AreaDto areaDto) {
        // 设置创建时间和更新时间
        areaDto.setCreateTime(LocalDate.now());
        areaDto.setUpdateTime(LocalDate.now());

        // 如果状态为空，默认设置为启用
        if (areaDto.getStatus() == null) {
            areaDto.setStatus(StatusEnums.ENABLED);
        }

        boolean saved = areaService.saveArea(areaDto);
        if (saved) {
            return Result.success(null, "添加成功");
        } else {
            return Result.error(500, "添加失败");
        }
    }

    /**
     * 修改区域信息
     *
     * @param areaDto 区域信息
     * @param id      区域ID
     * @return 修改结果
     */
    @PreAuthorize("hasAuthority('location:area:update')")
    @PutMapping("/{id}")
    public Result<String> updateArea(@RequestBody AreaDto areaDto, @PathVariable String id) {
        // 设置ID和更新时间
        areaDto.setId(id);
        areaDto.setUpdateTime(LocalDate.now());

        // 执行更新操作
        boolean updated = areaService.updateArea(areaDto);
        if (updated) {
            return Result.success(null, "修改成功");
        } else {
            return Result.error(500, "修改失败");
        }
    }

    /**
     * 删除区域（逻辑删除，实际是将状态设置为禁用）
     *
     * @param id 区域ID
     * @return 删除结果
     */
    @PreAuthorize("hasAuthority('location:area:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteArea(@PathVariable String id) {
        // 将状态设置为禁用，实现逻辑删除
        boolean updated = areaService.updateStatus(id, StatusEnums.DISABLED);
        if (updated) {
            return Result.success(null, "删除成功");
        } else {
            return Result.error(500, "删除失败");
        }
    }

    /**
     * 检查AreaCode是否重复
     *
     * @param areaCode 区域编码
     * @return 检查结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkAreaCode")
    public Result<Boolean> checkAreaCode(@RequestParam String areaCode) {
        boolean exists = areaService.checkAreaCode(areaCode);
        return Result.success(exists, "检查成功");
    }

    /**
     * 检查AreaName是否重复
     *
     * @param areaName 区域编码
     * @return 检查结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkAreaName")
    public Result<Boolean> checkAreaName(@RequestParam String areaName) {
        boolean exists = areaService.lambdaQuery().eq(Area::getAreaName, areaName).exists();
        return Result.success(exists, "查询成功");
    }

    /**
     * 获取所有区域的信息
     *
     * @return 返回所有区域的集合
     */
    @GetMapping("/getAreas")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Area>> getAreas() {
        List<Area> list = areaService.lambdaQuery()
                .eq(Area::getStatus, StatusEnums.ENABLED.getCode()).list();
        return Result.success(list, "查询成功");
    }
}
