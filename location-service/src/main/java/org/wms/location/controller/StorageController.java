package org.wms.location.controller;

import java.time.LocalDateTime;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.wms.api.client.ProductClient;
import org.wms.common.model.Result;
import org.wms.location.model.entity.Storage;
import org.wms.location.model.enums.LocationStatusEnums;
import org.wms.location.model.vo.StorageVo;
import org.wms.location.service.StorageLocationService;

@RestController
@RequestMapping("/location/storage")
public class StorageController {

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private ProductClient productClient;

    /**
     * 分页查询库位信息
     *
     * @param page         当前页
     * @param pageSize     每页大小
     * @param areaId       区域ID
     * @param shelfId      货架ID
     * @param status       状态
     * @param locationName 库位名称
     * @param productId    产品ID
     * @return 分页结果
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('location:storage:list')")
    public Result<IPage<StorageVo>> pageQuery(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String areaId,
            @RequestParam(required = false) String shelfId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String locationName,
            @RequestParam(required = false) String productId) {
        Page<StorageVo> pageParam = new Page<>(page, pageSize);
        IPage<StorageVo> result = storageLocationService.pageQuery(pageParam, areaId, shelfId, status, locationName, productId);
        result.getRecords().forEach(storageVo -> {
            if (StrUtil.isBlank(storageVo.getProductId())) {
                return;
            }
            storageVo.setProductName(productClient.getProductById(storageVo.getProductId()).getProductName());
        });
        return Result.success(result, "查询成功");
    }

    /**
     * 检查库位code是否存在
     *
     * @param locationCode 库位code
     * @return 结果
     */
    @GetMapping("/checkCode")
    @PreAuthorize("isAuthenticated()")
    public Result<Boolean> checkCode(@RequestParam String areaId,
                                     @RequestParam String shelfId,
                                     @RequestParam String locationCode) {
        boolean exists = storageLocationService.lambdaQuery()
                .eq(Storage::getAreaId, areaId)
                .eq(Storage::getShelfId, shelfId)
                .eq(Storage::getLocationCode, locationCode).exists();
        return Result.success(exists, "查询成功");
    }

    /**
     * 添加库位
     *
     * @param storage 库位信息
     * @return 结果
     */
    @PostMapping
    @PreAuthorize("hasAuthority('location:storage:add')")
    public Result<Boolean> add(@RequestBody Storage storage) {
        storage.setCreateTime(LocalDateTime.now());
        storage.setUpdateTime(LocalDateTime.now());
        boolean save = storageLocationService.save(storage);
        if (save) {
            return Result.success(true, "添加库位成功");
        }
        return Result.error(500, "添加库位信息失败");
    }

    /**
     * 更新库位
     *
     * @param id      库位ID
     * @param storage 库位信息
     * @return 结果
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('location:storage:update')")
    public Result<Boolean> update(@PathVariable String id, @RequestBody Storage storage) {
        storage.setUpdateTime(LocalDateTime.now());
        storage.setId(id);
        boolean update = storageLocationService.updateById(storage);
        if (update) {
            return Result.success(true, "更新库位成功");
        }
        return Result.error(500, "更新库位信息失败");
    }

    /**
     * 禁用库位
     *
     * @param id 库位ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('location:storage:delete')")
    public Result<Boolean> delete(@PathVariable String id) {
        boolean remove = storageLocationService.lambdaUpdate()
                .eq(Storage::getId, id)
                .set(Storage::getStatus, LocationStatusEnums.DISABLED.getCode())
                .update();
        if (remove) {
            return Result.success(true, "禁用库位成功");
        }
        return Result.error(500, "禁用库位失败");
    }

    /**
     * 批量添加库位信息
     *
     * @param storageList 库位信息列表
     * @return 结果
     */
    @PostMapping("/batch")
    @PreAuthorize("hasAuthority('location:storage:add')")
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> batchAdd(@RequestBody List<Storage> storageList) {
        storageList.forEach(item -> {
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateTime(LocalDateTime.now());
        });
        boolean save = storageLocationService.saveBatch(storageList);
        if (save) {
            return Result.success(true, "批量添加库位成功");
        }
        return Result.error(500, "批量添加库位失败");
    }

}
