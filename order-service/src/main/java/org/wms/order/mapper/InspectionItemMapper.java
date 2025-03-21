package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.InspectionItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【quality_inspection_item(质检明细表)】的数据库操作Mapper
* @createDate 2025-03-21 10:02:29
* @Entity org.wms.order.model.entity.InspectionItem
*/
@Mapper
public interface InspectionItemMapper extends BaseMapper<InspectionItem> {

}




