package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.service.OrderInItemService;
import org.wms.order.mapper.OrderInItemMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【order_in_item(入库订单明细表)】的数据库操作Service实现
* @createDate 2025-03-12 20:41:27
*/
@Service
public class OrderInItemServiceImpl extends ServiceImpl<OrderInItemMapper, OrderInItem>
    implements OrderInItemService{

}




