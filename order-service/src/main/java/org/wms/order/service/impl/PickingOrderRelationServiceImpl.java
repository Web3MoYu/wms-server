package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.PickingOrderRelation;
import org.wms.order.service.PickingOrderRelationService;
import org.wms.order.mapper.PickingOrderRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【picking_order_relation(拣货单-订单关联表)】的数据库操作Service实现
* @createDate 2025-04-12 14:41:26
*/
@Service
public class PickingOrderRelationServiceImpl extends ServiceImpl<PickingOrderRelationMapper, PickingOrderRelation>
    implements PickingOrderRelationService{

}




