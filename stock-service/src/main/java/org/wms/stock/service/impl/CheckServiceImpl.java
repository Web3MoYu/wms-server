package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.UserClient;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.sys.User;
import org.wms.stock.model.dto.CheckQueryDto;
import org.wms.stock.model.entity.Check;
import org.wms.stock.model.vo.CheckVo;
import org.wms.stock.service.CheckService;
import org.wms.stock.mapper.CheckMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author moyu
 * @description 针对表【stock_check(盘点单表)】的数据库操作Service实现
 * @createDate 2025-05-05 09:16:05
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check>
        implements CheckService {

    @Resource
    UserClient userClient;

    @Resource
    LocationClient locationClient;

    @Override
    public Page<CheckVo> pageList(CheckQueryDto dto) {
        Page<Check> page = this.lambdaQuery()
                .like(StringUtils.hasText(dto.getCheckNo()), Check::getCheckNo, dto.getCheckNo())
                .eq(StringUtils.hasText(dto.getAreaId()), Check::getAreaId, dto.getAreaId())
                .eq(StringUtils.hasText(dto.getChecker()), Check::getChecker, dto.getChecker())
                .eq(StringUtils.hasText(dto.getCreator()), Check::getCreator, dto.getCreator())
                .ge(Objects.nonNull(dto.getPlanStartTime()), Check::getPlanStartTime, dto.getPlanStartTime())
                .le(Objects.nonNull(dto.getPlanEndTime()), Check::getPlanEndTime, dto.getPlanEndTime())
                .ge(Objects.nonNull(dto.getActualStartTime()), Check::getActualStartTime, dto.getActualStartTime())
                .le(Objects.nonNull(dto.getActualEndTime()), Check::getActualEndTime, dto.getActualEndTime())
                .eq(Objects.nonNull(dto.getStatus()), Check::getStatus, dto.getStatus())
                .orderByDesc(Check::getCreateTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));
        List<CheckVo> list = page.getRecords().stream().map(this::assembleCheckVo).toList();
        Page<CheckVo> pageVo = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        pageVo.setRecords(list);
        return pageVo;
    }


    public CheckVo assembleCheckVo(Check check) {
        CheckVo vo = new CheckVo();
        BeanUtils.copyProperties(check, vo);

        Area area = locationClient.getArea(check.getAreaId());
        vo.setArea(area);

        User checker = userClient.getUserById(check.getChecker());
        User creator = userClient.getUserById(check.getCreator());
        vo.setCheckerUser(checker);
        vo.setCreatorUser(creator);
        return vo;
    }
}




