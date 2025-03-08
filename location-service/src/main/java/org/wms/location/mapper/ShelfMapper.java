package org.wms.location.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.location.model.entity.Shelf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author moyu
* @description 针对表【wms_shelf(货架表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.Shelf
*/
@Mapper
public interface ShelfMapper extends BaseMapper<Shelf> {

}




