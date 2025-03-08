package org.wms.location.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.location.model.entity.StorageLocation;
import org.wms.location.service.StorageLocationService;
import org.wms.location.mapper.StorageLocationMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【wms_storage_location(库位表)】的数据库操作Service实现
* @createDate 2025-03-08 17:25:49
*/
@Service
public class StorageLocationServiceImpl extends ServiceImpl<StorageLocationMapper, StorageLocation>
    implements StorageLocationService{

}




