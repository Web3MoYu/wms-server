package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.stock.model.entity.Stock;
import org.wms.stock.service.StockService;
import org.wms.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【stock(库存表)】的数据库操作Service实现
* @createDate 2025-03-10 22:19:37
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
    implements StockService{

}




