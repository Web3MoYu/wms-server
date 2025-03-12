package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.service.OrderInService;
import org.wms.order.mapper.OrderInMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【order_in(入库订单表)】的数据库操作Service实现
* @createDate 2025-03-12 20:41:27
*/
@Service
public class OrderInServiceImpl extends ServiceImpl<OrderInMapper, OrderIn>
    implements OrderInService{

}




