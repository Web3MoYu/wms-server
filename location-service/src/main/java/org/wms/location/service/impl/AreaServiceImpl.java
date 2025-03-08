package org.wms.location.service.impl;

import org.springframework.stereotype.Service;
import org.wms.location.mapper.AreaMapper;
import org.wms.location.model.entity.Area;
import org.wms.location.model.enums.StatusEnums;
import org.wms.location.model.vo.AreaVo;
import org.wms.location.service.AreaService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【wms_area(区域表)】的数据库操作Service实现
 * @createDate 2025-03-08 17:25:49
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
        implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public Page<AreaVo> pageAreaVos(int page, int pageSize, String areaName, String areaManager, StatusEnums status) {
        // 创建分页对象
        Page<Area> pageParam = new Page<>(page, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<Area> queryWrapper = new LambdaQueryWrapper<>();

        // 区域名称模糊查询
        if (areaName != null && !areaName.isEmpty()) {
            queryWrapper.like(Area::getAreaName, areaName);
        }

        // 负责人查询
        if (areaManager != null && !areaManager.isEmpty()) {
            queryWrapper.eq(Area::getAreaManager, areaManager);
        }

        // 状态查询
        if (status != null) {
            queryWrapper.eq(Area::getStatus, status);
        }

        // 排序（按创建时间降序）
        queryWrapper.orderByDesc(Area::getCreateTime);

        // 使用连表查询直接获取包含负责人名称的区域信息
        return areaMapper.selectAreaVoPage(pageParam, queryWrapper);
    }
}




