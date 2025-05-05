package org.wms.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.stock.model.entity.Check;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【stock_check(盘点单表)】的数据库操作Mapper
 * @createDate 2025-05-05 09:16:05
 * @Entity org.wms.stock.model.entity.Check
 */
@Mapper
public interface CheckMapper extends BaseMapper<Check> {

}




