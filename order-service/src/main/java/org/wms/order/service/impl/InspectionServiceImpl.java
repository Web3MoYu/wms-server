package org.wms.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wms.api.client.UserClient;
import org.wms.common.entity.sys.User;
import org.wms.common.model.Result;
import org.wms.order.mapper.InspectionMapper;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.InspectionService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Service实现
 * @createDate 2025-03-21 10:02:29
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection>
        implements InspectionService {

    @Resource
    private UserClient userClient;

    @Override
    public Page<InspectionVo> pageList(InspectionDto dto) {
        // 构建查询条件
        LambdaQueryWrapper<Inspection> queryWrapper = new LambdaQueryWrapper<>();

        // 质检编号模糊搜索
        if (StringUtils.hasText(dto.getInspectionNo())) {
            queryWrapper.like(Inspection::getInspectionNo, dto.getInspectionNo());
        }

        // 质检类型：1-入库质检，2-出库质检，3-库存质检,为null查询全部
        if (dto.getInspectionType() != null) {
            queryWrapper.eq(Inspection::getInspectionType, dto.getInspectionType());
        }

        // 订单编号，模糊搜索
        if (StringUtils.hasText(dto.getRelatedOrderNo())) {
            queryWrapper.like(Inspection::getRelatedOrderNo, dto.getRelatedOrderNo());
        }

        // 质检员
        if (StringUtils.hasText(dto.getInspector())) {
            queryWrapper.eq(Inspection::getInspector, dto.getInspector());
        }

        // 质检时间范围
        if (dto.getStartTime() != null) {
            queryWrapper.ge(Inspection::getInspectionTime, dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            queryWrapper.le(Inspection::getInspectionTime, dto.getEndTime());
        }

        // 质检状态：1-通过，2-不通过，3-部分异常,为null查询全部
        if (dto.getStatus() != null) {
            queryWrapper.eq(Inspection::getStatus, dto.getStatus());
        }

        // 排序：创建时间升序或降序
        if (dto.getCreateTimeAsc() != null) {
            if (dto.getCreateTimeAsc()) {
                queryWrapper.orderByAsc(Inspection::getCreateTime);
            } else {
                queryWrapper.orderByDesc(Inspection::getCreateTime);
            }
        } else {
            // 默认降序
            queryWrapper.orderByDesc(Inspection::getCreateTime);
        }

        // 执行分页查询
        Page<Inspection> page = new Page<>(dto.getPage(), dto.getPageSize());
        Page<Inspection> pageResult = this.page(page, queryWrapper);

        // 将查询结果转换为VO对象
        List<InspectionVo> list = pageResult.getRecords().stream().map(item -> {
            InspectionVo vo = new InspectionVo();
            // 复制基本属性
            BeanUtils.copyProperties(item, vo);

            // 获取质检员信息
            if (StringUtils.hasText(item.getInspector())) {
                User user = userClient.getUserById(item.getInspector());
                vo.setInspectorInfo(user);
            }

            return vo;
        }).toList();
        Page<InspectionVo> res = new Page<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        res.setRecords(list);
        return res;
    }
}




