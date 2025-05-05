package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.stock.model.entity.CheckItem;
import org.wms.stock.service.CheckItemService;
import org.wms.stock.mapper.CheckItemMapper;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @description 针对表【stock_check_item(盘点明细表)】的数据库操作Service实现
 * @createDate 2025-05-05 09:16:05
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem>
        implements CheckItemService {

}




