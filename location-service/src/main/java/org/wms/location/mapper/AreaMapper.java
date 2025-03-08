package org.wms.location.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.location.model.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【wms_area(区域表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.Area
*/
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

}




