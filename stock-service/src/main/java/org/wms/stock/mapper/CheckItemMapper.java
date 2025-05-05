package org.wms.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.stock.model.entity.CheckItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【stock_check_item(盘点明细表)】的数据库操作Mapper
 * @createDate 2025-05-05 09:16:05
 * @Entity org.wms.stock.model.entity.CheckItem
 */
@Mapper
public interface CheckItemMapper extends BaseMapper<CheckItem> {

}




