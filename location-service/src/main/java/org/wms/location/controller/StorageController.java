package org.wms.location.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.api.client.InspectClient;
import org.wms.api.client.ProductClient;
import org.wms.common.entity.order.InspectionItem;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.model.Location;
import org.wms.common.model.Result;
import org.wms.location.model.entity.Storage;
import org.wms.location.model.vo.StorageVo;
import org.wms.location.service.StorageLocationService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

@RestController
@RequestMapping("/location/storage")
public class StorageController {

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private ProductClient productClient;

    @Resource
    private InspectClient inspectClient;

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
        storage.setCreateTime(LocalDate.now());
        storage.setUpdateTime(LocalDate.now());
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
        storage.setUpdateTime(LocalDate.now());
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
            item.setUpdateTime(LocalDate.now());
            item.setCreateTime(LocalDate.now());
        });
        boolean save = storageLocationService.saveBatch(storageList);
        if (save) {
            return Result.success(true, "批量添加库位成功");
        }
        return Result.error(500, "批量添加库位失败");
    }

    /**
     * 按照货架id列表查找库位信息
     *
     * @param id id
     * @return 结果
     */
    @GetMapping("/getStorageById/{id}")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Storage>> getStorageById(@PathVariable String id) {
        List<Storage> list = storageLocationService
                .lambdaQuery()
                .eq(Storage::getShelfId, id)
                .eq(Storage::getStatus, LocationStatusEnums.FREE.getCode())
                .list();
        return Result.success(list, "查询成功");
    }


    /**
     * 按照货架id和产品id查找库位信息
     *
     * @param id id
     * @return 结果
     */
    @GetMapping("/getStorageByIdAndItemId/{id}/{itemId}")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Storage>> getStorageByIdAndItemId(@PathVariable String id, @PathVariable String itemId) {
        InspectionItem itemById = inspectClient.getItemById(itemId);
        List<Location> locations = itemById.getLocation();
        List<String> storages = new ArrayList<>();
        for (Location locationItem : locations) {
            if (locationItem.getShelfId().equals(id)) {
                storages = locationItem.getStorageIds();
            }
        }
        List<String> finalStorages = storages;
        List<Storage> list = storageLocationService
                .lambdaQuery()
                .eq(Storage::getShelfId, id)
                .and(wrapper -> wrapper
                        .eq(Storage::getStatus, LocationStatusEnums.FREE.getCode())
                        .or(!finalStorages.isEmpty())
                        .in(!finalStorages.isEmpty(), Storage::getId, finalStorages))
                .list();
        return Result.success(list, "查询成功");
    }

    /**
     * 根据ids 查询库位列表
     *
     * @param ids id列表
     * @return 库位列表信息
     */
    @GetMapping("/getStoragesByIds")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Storage>> getStoragesByIds(@RequestParam List<String> ids) {
        List<Storage> list = storageLocationService
                .lambdaQuery()
                .in(Storage::getId, ids)
                .list();
        return Result.success(list, "查询成功");
    }

}
