package org.wms.location.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.location.model.entity.AreaInspector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【wms_area_inspector(区域质检员关系表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.AreaInspector
*/
@Mapper
public interface AreaInspectorMapper extends BaseMapper<AreaInspector> {

}




