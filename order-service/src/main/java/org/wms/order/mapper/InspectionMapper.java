package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.Inspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Mapper
 * @createDate 2025-03-21 10:02:29
 * @Entity org.wms.order.model.entity.Inspection
 */
@Mapper
public interface InspectionMapper extends BaseMapper<Inspection> {

}




