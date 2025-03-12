package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.OrderInItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【order_in_item(入库订单明细表)】的数据库操作Mapper
 * @createDate 2025-03-12 20:41:27
 * @Entity org.wms.order.model.entity.OrderInItem
 */
@Mapper
public interface OrderInItemMapper extends BaseMapper<OrderInItem> {

}




