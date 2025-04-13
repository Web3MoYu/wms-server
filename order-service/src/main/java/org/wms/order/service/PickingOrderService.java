package org.wms.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.order.model.vo.PickingDetailVo;
import org.wms.order.model.vo.PickingOrderVo;

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
     * @param ids 出库订单ID列表
     * @return 是否增加成功。
     */
    Result<String> batchAddPickings(List<String> ids, String picker);


    /**
     * 获取拣货单详情信息
     *
     * @param pickingId 拣货id
     * @return 拣货的详细信息
     */
    Result<List<PickingDetailVo>> detail(String pickingId);
}
