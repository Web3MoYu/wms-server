package org.wms.location.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.location.model.entity.Area;
import org.wms.location.service.AreaService;
import org.wms.location.mapper.AreaMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【wms_area(区域表)】的数据库操作Service实现
* @createDate 2025-03-08 17:25:49
*/
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
    implements AreaService{

}




