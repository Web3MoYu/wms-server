package org.wms.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.common.entity.stock.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【stock(库存表)】的数据库操作Mapper
* @createDate 2025-03-10 22:19:37
* @Entity org.wms.common.entity.stock.Stock
*/
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}




