package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.stock.model.entity.Movement;
import org.wms.stock.service.MovementService;
import org.wms.stock.mapper.MovementMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【stock_movement(库位变动记录表)】的数据库操作Service实现
* @createDate 2025-05-03 22:29:31
*/
@Service
public class MovementServiceImpl extends ServiceImpl<MovementMapper, Movement>
    implements MovementService{

}




