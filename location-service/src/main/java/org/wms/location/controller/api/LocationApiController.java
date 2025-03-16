package org.wms.location.controller.api;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.location.Area;
import org.wms.common.model.Location;
import org.wms.common.model.vo.LocationVo;
import org.wms.location.model.entity.Shelf;
import org.wms.location.model.entity.Storage;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.location.service.AreaService;
import org.wms.location.service.ShelfService;
import org.wms.location.service.StorageLocationService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/location")
public class LocationApiController {

    @Resource
    ShelfService service;

    @Resource
    StorageLocationService storageService;

    @Resource
    AreaService areaService;

    /**
     * 获取位置列表
     *
     * @param location
     * @return
     */
    @PostMapping("/getLocations")
    public LocationVo getLocations(@RequestBody Location location) {
        String shelfId = location.getShelfId();
        List<String> storageIds = location.getStorageIds();
        Shelf shelf = service.lambdaQuery()
                .select(Shelf::getShelfName)
                .eq(Shelf::getId, shelfId).one();
        List<Storage> list = storageService.lambdaQuery()
                .select(Storage::getLocationName)
                .in(Storage::getId, storageIds).list();
        Set<String> collect = list.stream().map(Storage::getLocationName).collect(Collectors.toSet());

        LocationVo locationVo = new LocationVo();
        locationVo.setShelfName(shelf.getShelfName());
        locationVo.setStorageNames(collect);

        return locationVo;
    }

    /**
     * 根据id获取区域信息
     *
     * @param id id
     * @return 区域信息
     */
    @PostMapping("/getArea/{id}")
    public Area getArea(@PathVariable String id) {
        return areaService.getById(id);
    }

    /**
     * 更改库位状态
     *
     * @param location 位置
     * @return 处理结果
     */
    @PostMapping("/updateStatusInStorage")
    public boolean updateStatusInStorage(@RequestBody Location location, @RequestParam("status") Integer type,
                                         @RequestParam("productId") String productId) {
        LambdaUpdateWrapper<Storage> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Storage::getShelfId, location.getShelfId())
                .in(Storage::getId, location.getStorageIds());
        if (Objects.equals(type, LocationStatusEnums.DISABLED.getCode())) {
            wrapper.set(Storage::getStatus, LocationStatusEnums.DISABLED.getCode());
        }
        if (Objects.equals(type, LocationStatusEnums.FREE.getCode())) {
            wrapper.set(Storage::getStatus, LocationStatusEnums.FREE.getCode())
                    .set(Storage::getProductId, null);
        }
        if (Objects.equals(type, LocationStatusEnums.OCCUPIED.getCode())) {
            wrapper.set(Storage::getStatus, LocationStatusEnums.OCCUPIED.getCode())
                    .set(Storage::getProductId, productId);
        }
        return storageService.update(wrapper);
    }
}
