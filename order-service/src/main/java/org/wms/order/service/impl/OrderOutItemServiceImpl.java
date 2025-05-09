package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.service.OrderOutItemService;
import org.wms.order.mapper.OrderOutItemMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【order_out_item(出库订单明细表)】的数据库操作Service实现
* @createDate 2025-03-12 20:41:27
*/
@Service
public class OrderOutItemServiceImpl extends ServiceImpl<OrderOutItemMapper, OrderOutItem>
    implements OrderOutItemService{

}




