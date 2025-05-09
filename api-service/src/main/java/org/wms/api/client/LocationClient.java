package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.sys.User;
import org.wms.common.model.Location;
import org.wms.common.model.vo.LocationInfo;
import org.wms.common.model.vo.LocationVo;

import java.util.Collection;

@FeignClient(value = "location-service", contextId = "location")
public interface LocationClient {

    /**
     * 获取位置列表
     *
     * @param location
     * @return
     */
    @PostMapping("/api/location/getLocations")
    LocationVo getLocations(@RequestBody Location location);

    /**
     * 根据id获取区域信息
     *
     * @param id id
     * @return 区域信息
     */
    @PostMapping("/api/location/getArea/{id}")
    Area getArea(@PathVariable String id);

    /**
     * 更改库位状态
     *
     * @param location 位置
     * @return 处理结果
     */
    @PostMapping("/api/location/updateStatusInStorage")
    boolean updateStatusInStorage(@RequestBody Location location, @RequestParam("status") Integer status,
                                  @RequestParam(value = "productId", required = false) String productId);

    /**
     * 获取位置的详细信息
     *
     * @param location 位置id信息
     * @return 详细信息
     */
    @PostMapping("/api/location/getLocationInfo")
    LocationInfo getLocationInfo(@RequestBody Location location);

    /**
     * 更改库位状态
     *
     * @return 处理结果
     */
    @PostMapping("/api/location/updateStatusInIds")
    boolean updateStatusInIds(@RequestBody Collection<String> ids, @RequestParam("status") Integer type,
                              @RequestParam(value = "productId", required = false) String productId);

    /**
     * 获取该区域的主要质检员
     *
     * @param areaId 区域ID
     * @return User
     */
    @GetMapping("/api/location/getMainInspector/{areaId}")
    User getMainInspector(@PathVariable String areaId);
}
