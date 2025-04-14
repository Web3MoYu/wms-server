package org.wms.location.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.wms.location.mapper.ShelfMapper;
import org.wms.location.mapper.StorageLocationMapper;
import org.wms.common.entity.location.Shelf;
import org.wms.common.entity.location.StatusEnums;
import org.wms.location.model.vo.ShelfVo;
import org.wms.location.service.ShelfService;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【wms_shelf(货架表)】的数据库操作Service实现
 * @createDate 2025-03-08 17:25:49
 */
@Service
public class ShelfServiceImpl extends ServiceImpl<ShelfMapper, Shelf>
        implements ShelfService {

    @Resource
    ShelfMapper shelfMapper;

    @Resource
    StorageLocationMapper storageMapper;

    @Override
    public Page<ShelfVo> pageShelfVos(int page, int pageSize, String shelfName, String areaId, Integer status) {
        // 创建分页对象
        Page<Shelf> pageParam = new Page<>(page, pageSize);

        // 直接调用Mapper的连表查询方法
        return shelfMapper.selectShelfVoPage(pageParam, shelfName, areaId, status);
    }

    @Override
    public boolean updateStatus(String id, StatusEnums status) {
        LambdaUpdateWrapper<Shelf> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Shelf::getId, id)
                .set(Shelf::getStatus, status)
                .set(Shelf::getUpdateTime, LocalDate.now());

        return update(updateWrapper);
    }

    @Override
    public boolean checkShelfCode(String areaId, String shelfCode) {
        return lambdaQuery()
                .eq(Shelf::getShelfCode, shelfCode)
                .eq(Shelf::getAreaId, areaId)
                .exists();
    }

    @Override
    public boolean checkShelfName(String areaId, String shelfName) {
        return lambdaQuery()
                .eq(Shelf::getAreaId, areaId)
                .eq(Shelf::getShelfName, shelfName)
                .exists();
    }

    @Override
    public List<Shelf> listFreeShelves(String areaId) {
        return storageMapper.listFreeShelves(areaId);
    }
}




