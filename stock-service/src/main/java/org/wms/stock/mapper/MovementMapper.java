package org.wms.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.stock.model.entity.Movement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【stock_movement(库位变动记录表)】的数据库操作Mapper
* @createDate 2025-05-03 22:29:31
* @Entity org.wms.stock.model.entity.Movement
*/
@Mapper
public interface MovementMapper extends BaseMapper<Movement> {

}




