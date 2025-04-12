package org.wms.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.order.model.dto.PickingOrderDto;
import org.wms.order.model.entity.PickingOrder;
import com.baomidou.mybatisplus.extension.service.IService;

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
    Result<Page<PickingOrder>> pageList(PickingOrderDto dto);
}
