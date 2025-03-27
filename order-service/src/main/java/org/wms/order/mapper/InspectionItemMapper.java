package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.common.entity.order.InspectionItem;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【quality_inspection_item(质检明细表)】的数据库操作Mapper
 * @createDate 2025-03-21 10:02:29
 * @Entity org.wms.common.entity.order.InspectionItem
 */
@Mapper
public interface InspectionItemMapper extends BaseMapper<InspectionItem> {

    /**
     * 修改质检项状态和数量
     *
     * @param remark 备注
     * @param id     质检项ID
     * @param status 质检状态
     * @param count  质检数量
     * @return 是否成功
     */
    boolean updateItemStatusAndCount(@Param("remark") String remark, @Param("id") String id,
                                     @Param("status") Integer status, @Param("count") Integer count);
}
