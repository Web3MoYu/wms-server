package org.wms.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wms.common.entity.location.Area;
import org.wms.common.model.Location;
import org.wms.common.model.vo.LocationVo;

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
                                  @RequestParam("productId") String productId);
}
