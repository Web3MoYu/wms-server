package org.wms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.order.model.entity.PickingItem;
import org.wms.order.service.PickingItemService;
import org.wms.order.mapper.PickingItemMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【picking_item(拣货明细表)】的数据库操作Service实现
* @createDate 2025-04-12 14:41:26
*/
@Service
public class PickingItemServiceImpl extends ServiceImpl<PickingItemMapper, PickingItem>
    implements PickingItemService{

}




