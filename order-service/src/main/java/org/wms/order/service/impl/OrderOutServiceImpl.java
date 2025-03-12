package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.service.OrderOutService;
import org.wms.order.mapper.OrderOutMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【order_out(出库订单表)】的数据库操作Service实现
* @createDate 2025-03-12 20:41:27
*/
@Service
public class OrderOutServiceImpl extends ServiceImpl<OrderOutMapper, OrderOut>
    implements OrderOutService{

}




