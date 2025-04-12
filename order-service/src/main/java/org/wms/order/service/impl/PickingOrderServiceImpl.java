package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.PickingOrder;
import org.wms.order.service.PickingOrderService;
import org.wms.order.mapper.PickingOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【picking_order(拣货单表)】的数据库操作Service实现
* @createDate 2025-04-12 14:41:26
*/
@Service
public class PickingOrderServiceImpl extends ServiceImpl<PickingOrderMapper, PickingOrder>
    implements PickingOrderService{

}




