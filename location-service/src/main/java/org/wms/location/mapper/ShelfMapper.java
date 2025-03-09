package org.wms.location.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.location.model.entity.Shelf;
import org.wms.location.model.vo.ShelfVo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author moyu
* @description 针对表【wms_shelf(货架表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.Shelf
*/
@Mapper
public interface ShelfMapper extends BaseMapper<Shelf> {

    /**
     * 分页查询货架信息，包含区域名称
     * 
     * @param page 分页参数
     * @param shelfName 货架名称（可选，模糊查询）
     * @param areaId 区域ID（可选）
     * @param status 状态（可选）
     * @return 包含区域名称的货架分页结果
     */
    Page<ShelfVo> selectShelfVoPage(
            @Param("page") Page<Shelf> page, 
            @Param("shelfName") String shelfName, 
            @Param("areaId") String areaId, 
            @Param("status") Integer status);
}




