package org.wms.location.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.location.model.entity.Area;
import org.wms.location.model.vo.AreaVo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author moyu
* @description 针对表【wms_area(区域表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.Area
*/
@Mapper
public interface AreaMapper extends BaseMapper<Area> {
    
    /**
     * 分页查询区域信息，包含负责人名称
     * 
     * @param page 分页参数
     * @param queryWrapper 查询条件
     * @return 包含负责人名称的区域分页结果
     */
    Page<AreaVo> selectAreaVoPage(Page<Area> page, @Param("ew") Wrapper<Area> queryWrapper);
}




