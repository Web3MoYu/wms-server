package org.wms.location.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.location.model.entity.Shelf;
import org.wms.location.service.ShelfService;
import org.wms.location.mapper.ShelfMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【wms_shelf(货架表)】的数据库操作Service实现
* @createDate 2025-03-08 17:25:49
*/
@Service
public class ShelfServiceImpl extends ServiceImpl<ShelfMapper, Shelf>
    implements ShelfService{

}




