package org.wms.location.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wms.common.entity.sys.User;
import org.wms.common.exception.BizException;
import org.wms.location.mapper.AreaInspectorMapper;
import org.wms.location.mapper.AreaMapper;
import org.wms.location.model.dto.AreaDto;
import org.wms.common.entity.location.Area;
import org.wms.location.model.entity.AreaInspector;
import org.wms.location.model.enums.IsPrimaryEnums;
import org.wms.common.entity.location.StatusEnums;
import org.wms.location.model.vo.AreaVo;
import org.wms.location.service.AreaService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
    AreaMapper areaMapper;

    @Resource
    AreaInspectorMapper areaInspectorMapper;

    @Override
    public Page<AreaVo> pageAreaVos(int page, int pageSize, String areaName, String areaManager, Integer status) {
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
        Page<AreaVo> areaVoPage = areaMapper.selectAreaVoPage(pageParam, queryWrapper);
        // 查询每隔区域的质检员信息
        areaVoPage.getRecords().forEach(areaVo -> {
            LambdaQueryWrapper<AreaInspector> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AreaInspector::getAreaId, areaVo.getId());
            List<AreaInspector> areaInspectors = areaInspectorMapper.selectList(wrapper);
            areaVo.setInspectors(areaInspectors);
        });
        return areaVoPage;
    }

    @Override
    public boolean updateStatus(String id, StatusEnums status) {
        LambdaUpdateWrapper<Area> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Area::getId, id)
                .set(Area::getStatus, status.getCode())
                .set(Area::getUpdateTime, LocalDate.now());

        return update(updateWrapper);
    }

    @Override
    public boolean checkAreaCode(String areaCode) {
        LambdaQueryWrapper<Area> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Area::getAreaCode, areaCode);
        return this.exists(queryWrapper);
    }

    @Override
    public boolean saveArea(AreaDto areaDto) {
        // 保存区域信息
        boolean areaSaved = this.save(areaDto);
        if (!areaSaved) {
            throw new BizException(501, "保存区域失败");
        }
        // 保存质检员信息
        areaInspectorMapper.insert(getInspector(areaDto));
        return true;
    }

    @Override
    public boolean updateArea(AreaDto areaDto) {
        // 删除质检员
        LambdaQueryWrapper<AreaInspector> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AreaInspector::getAreaId, areaDto.getId());
        areaInspectorMapper.delete(wrapper);

        // 修改区域信息
        areaMapper.updateById(areaDto);
        // 保存质检员信息
        areaInspectorMapper.insert(getInspector(areaDto));

        return true;
    }

    // 转换AreaDto为AreaInspector
    private List<AreaInspector> getInspector(AreaDto areaDto) {
        // 转换主要质检员
        List<AreaInspector> result = new ArrayList<>();
        result.add(convertToAreaInspector(areaDto, areaDto.getPrimaryUser(), IsPrimaryEnums.YES));
        for (User user : areaDto.getSecondaryUsers()) {
            AreaInspector inspector = convertToAreaInspector(areaDto, user, IsPrimaryEnums.NO);
            result.add(inspector);
        }
        return result;
    }

    private AreaInspector convertToAreaInspector(AreaDto areaDto, User user, IsPrimaryEnums isPrimaryEnum) {
        AreaInspector areaInspector = new AreaInspector();
        areaInspector.setAreaId(areaDto.getId());
        areaInspector.setAreaName(areaDto.getAreaName());
        areaInspector.setInspectorId(user.getUserId());
        areaInspector.setInspectorName(user.getRealName());
        areaInspector.setInspectorPhone(user.getPhone());
        if (isPrimaryEnum == IsPrimaryEnums.YES) {
            areaInspector.setIsPrimary(IsPrimaryEnums.YES);
        } else {
            areaInspector.setIsPrimary(IsPrimaryEnums.NO);
        }

        areaInspector.setCreateTime(LocalDate.now());
        areaInspector.setUpdateTime(LocalDate.now());
        return areaInspector;
    }
}




