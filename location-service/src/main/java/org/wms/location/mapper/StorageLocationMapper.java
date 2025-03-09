package org.wms.location.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.location.model.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wms.location.model.vo.StorageVo;

/**
* @author moyu
* @description 针对表【wms_storage_location(库位表)】的数据库操作Mapper
* @createDate 2025-03-08 17:25:49
* @Entity org.wms.location.model.entity.StorageLocation
*/
@Mapper
public interface StorageLocationMapper extends BaseMapper<Storage> {

    /**
     * 分页查询库位信息
     *
     * @param page 分页参数
     * @param areaId 区域ID
     * @param shelfId 货架ID
     * @param status 状态
     * @param locationName 库位名称
     * @param productId 产品ID
     * @return 分页结果
     */
    IPage<StorageVo> pageQuery(Page<StorageVo> page,
                              @Param("areaId") String areaId,
                              @Param("shelfId") String shelfId,
                              @Param("status") Integer status,
                              @Param("locationName") String locationName,
                              @Param("productId") String productId);
}




