package org.wms.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.order.model.dto.BatchAddPickingDto;
import org.wms.order.model.dto.PickingOneDto;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.order.model.vo.*;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【picking_order(拣货单表)】的数据库操作Service
 * @createDate 2025-04-12 14:41:26
 */
public interface PickingOrderService extends IService<PickingOrder> {

    /**
     * 分页查询拣货列表
     *
     * @param dto 分页条件
     * @return 分页数据
     */
    Result<Page<PickingOrderVo>> pageList(PickingOrderDto dto);

    /**
     * 批量增加拣货信息
     *
     * @param dto 新增信息
     * @return 是否增加成功。
     */
    Result<String> batchAddPickings(BatchAddPickingDto dto);


    /**
     * 获取拣货单详情信息
     *
     * @param pickingId 拣货id
     * @return 拣货的详细信息
     */
    Result<List<PickingDetailVo>> detail(String pickingId);

    /**
     * 根据出库订单 ID 获取出库位置信息
     *
     * @param orderId 出库订单ID
     * @return 位置信息
     */
    Result<List<PickingLocation>> getPickingLocation(String orderId);

    /**
     * 分拣
     *
     * @param dto 分拣数据
     * @return 是否分拣成功
     */
    Result<String> pickOne(List<PickingOneDto> dto);

    /**
     * 获取订单统计信息
     *
     * @param range 时间范围：1day, 1week, 1month, 3months, 6months
     * @return 统计信息
     */
    List<PickingStatisticsVo> getOrderStatistics(String range);
}
