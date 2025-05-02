package org.wms.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.stock.model.entity.Alert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【stock_alert(产品库存预警记录表)】的数据库操作Mapper
* @createDate 2025-05-02 19:07:21
* @Entity org.wms.stock.model.entity.Alert
*/
@Mapper
public interface AlertMapper extends BaseMapper<Alert> {

}




