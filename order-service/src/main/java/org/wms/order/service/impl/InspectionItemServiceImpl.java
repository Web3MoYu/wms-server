package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.common.entity.order.InspectionItem;
import org.wms.order.service.InspectionItemService;
import org.wms.order.mapper.InspectionItemMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【quality_inspection_item(质检明细表)】的数据库操作Service实现
* @createDate 2025-03-21 10:02:29
*/
@Service
public class InspectionItemServiceImpl extends ServiceImpl<InspectionItemMapper, InspectionItem>
    implements InspectionItemService{

}




