package org.wms.location.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.wms.common.entity.location.Storage;
import org.wms.location.model.vo.StorageVo;
import org.wms.location.service.StorageLocationService;
import org.wms.location.mapper.StorageLocationMapper;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @description 针对表【wms_storage_location(库位表)】的数据库操作Service实现
 * @createDate 2025-03-08 17:25:49
 */
@Service
public class StorageLocationServiceImpl extends ServiceImpl<StorageLocationMapper, Storage>
        implements StorageLocationService {

    @Resource
    private StorageLocationMapper storageLocationMapper;

    @Override
    public IPage<StorageVo> pageQuery(Page<StorageVo> page, String areaId, String shelfId, Integer status,
                                      String locationName, String productId) {
        return storageLocationMapper.pageQuery(page, areaId, shelfId, status, locationName, productId);
    }
}




