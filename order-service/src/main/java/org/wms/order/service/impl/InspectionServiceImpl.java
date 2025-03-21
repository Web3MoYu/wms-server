package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.Inspection;
import org.wms.order.service.InspectionService;
import org.wms.order.mapper.InspectionMapper;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Service实现
 * @createDate 2025-03-21 10:02:29
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection>
        implements InspectionService {

}




