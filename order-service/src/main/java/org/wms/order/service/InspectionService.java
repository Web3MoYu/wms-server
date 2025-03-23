package org.wms.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.vo.InspectionVo;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Service
 * @createDate 2025-03-21 10:02:29
 */
public interface InspectionService extends IService<Inspection> {

    /**
     * 查询入库订单列表
     * 
     * @param dto 查询条件
     * @return 结果
     */
    Page<InspectionVo> pageList(InspectionDto dto);
}
