package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.stock.model.entity.Check;
import org.wms.stock.service.CheckService;
import org.wms.stock.mapper.CheckMapper;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @description 针对表【stock_check(盘点单表)】的数据库操作Service实现
 * @createDate 2025-05-05 09:16:05
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check>
        implements CheckService {

}




