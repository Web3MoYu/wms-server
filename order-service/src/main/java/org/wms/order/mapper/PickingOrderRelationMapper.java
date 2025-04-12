package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.PickingOrderRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【picking_order_relation(拣货单-订单关联表)】的数据库操作Mapper
 * @createDate 2025-04-12 14:41:26
 * @Entity org.wms.order.model.entity.PickingOrderRelation
 */
@Mapper
public interface PickingOrderRelationMapper extends BaseMapper<PickingOrderRelation> {

}




