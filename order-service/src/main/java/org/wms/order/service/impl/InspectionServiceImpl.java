package org.wms.order.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wms.api.client.LocationClient;
import org.wms.api.client.UserClient;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.order.mapper.InspectionItemMapper;
import org.wms.order.mapper.InspectionMapper;
import org.wms.order.model.dto.InBoundInspectDto;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.dto.ItemInspect;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.entity.InspectionItem;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.enums.InspectItemStatus;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.enums.QualityStatusEnums;
import org.wms.order.model.vo.InspectionDetailVo;
import org.wms.order.model.vo.InspectionVo;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.service.InspectionService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import jakarta.annotation.Resource;
import org.wms.order.service.OrderInItemService;
import org.wms.order.service.OrderInService;

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

    @Resource
    private OrderInService orderInService;

    @Resource
    private OrderInItemService orderInItemService;

    @Resource
    private InspectionItemMapper inspectionItemMapper;

    @Resource
    private LocationClient locationClient;

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

    @Override
    public Result<String> inBoundCheck(InBoundInspectDto dto) {
        int status = 0;
        List<ItemInspect> list = dto.getItemInspects();
        if (list == null || list.isEmpty()) {
            throw new BizException(500, "参数不合法");
        }
        // 判断是全失败、全成功、还是部分成功
        for (ItemInspect item : list) {
            if (item.isApproval()) {
                status++;
            }
        }
        // 获取质检订单
        Inspection one = this.lambdaQuery().eq(Inspection::getInspectionNo, dto.getInspectionNo()).one();
        // 修改状态
        // 全成功
        if (status == list.size()) {
            // 修改质检状态
            boolean update = this.updateInspectionStatus(dto.getRemark(), dto.getInspectionNo(), QualityStatusEnums.PASSED);
            // 订单状态
            orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
            // 订单质检状态
            boolean update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                    .set(OrderIn::getQualityStatus, QualityStatusEnums.PASSED.getCode()).update();
            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        } else if (status == 0) {
            boolean update = this.updateInspectionStatus(dto.getRemark(), dto.getInspectionNo(), QualityStatusEnums.FAILED);
            orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.COMPLETED);
            // 订单质检状态
            boolean update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                    .set(OrderIn::getQualityStatus, QualityStatusEnums.FAILED.getCode()).update();
            // 全失败
            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        } else {
            boolean update = this.updateInspectionStatus(dto.getRemark(),
                    dto.getInspectionNo(), QualityStatusEnums.PARTIALLY_EXCEPTIONAL);
            orderInService.updateStatus(one.getRelatedOrderId(), dto.getRemark(), OrderStatusEnums.IN_PROGRESS);
            // 订单质检状态
            boolean update1 = orderInService.lambdaUpdate().eq(OrderIn::getId, one.getRelatedOrderId())
                    .set(OrderIn::getQualityStatus, QualityStatusEnums.PARTIALLY_EXCEPTIONAL.getCode()).update();
            if (!update || !update1) {
                throw new BizException(303, "修改质检状态失败");
            }
        }
        // 遍历订单详情，修改状态
        list.forEach(item -> {
            // 通过
            boolean r1;
            boolean r2;
            if (item.isApproval()) {
                // 修改质检详情状态
                r1 = this.inspectionItemMapper.updateItemStatusAndCount(item.getRemark(), item.getItemId(),
                        InspectItemStatus.QUALIFIED.getCode(), item.getCount());

                // 修改订单详情状态
                r2 = orderInItemService.lambdaUpdate()
                        .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                        .eq(OrderInItem::getProductId, item.getProductId())
                        .set(OrderInItem::getRemark, item.getRemark())
                        .set(OrderInItem::getActualQuantity, item.getCount())
                        .set(OrderInItem::getQualityStatus, QualityStatusEnums.PASSED).update();

            } else {
                r1 = this.inspectionItemMapper.updateItemStatusAndCount(item.getRemark(), item.getItemId(),
                        InspectItemStatus.UNQUALIFIED.getCode(), item.getCount());
                // 修改订单详情状态
                r2 = orderInItemService.lambdaUpdate()
                        .eq(OrderInItem::getOrderId, one.getRelatedOrderId())
                        .eq(OrderInItem::getProductId, item.getProductId())
                        .set(OrderInItem::getActualQuantity, item.getCount())
                        .set(OrderInItem::getRemark, item.getRemark())
                        .set(OrderInItem::getQualityStatus, QualityStatusEnums.FAILED).update();
                // 将库位释放
                // 查询库位信息
                InspectionItem inspectionItem = inspectionItemMapper.selectById(item.getItemId());
                if (inspectionItem == null || inspectionItem.getLocation() == null || inspectionItem.getLocation().isEmpty()) {
                    throw new BizException(500, "业务异常");
                }
                inspectionItem.getLocation().forEach(location -> {
                    boolean b = locationClient.updateStatusInStorage(location, LocationStatusEnums.FREE.getCode(), null);
                    if (!b) {
                        throw new BizException(303, "修改库位信息失败");
                    }
                });
            }
            if (!r1 || !r2) {
                throw new BizException(303, "修改详情状态失败");
            }
        });
        return Result.success(null, "质检成功");
    }

    @Override
    public Result<InspectionDetailVo<OrderInItem>> inDetail(String id) {
        Inspection one = this.lambdaQuery().eq(Inspection::getId, id).one();
        Result<List<OrderDetailVo<OrderInItem>>> result = orderInService.inDetail(one.getRelatedOrderId());
        if (result.getCode() != 200) {
            throw new BizException(303, "查询出错");
        }
        List<OrderDetailVo<OrderInItem>> data = result.getData();
        // 查询质检详情
        LambdaQueryWrapper<InspectionItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InspectionItem::getInspectionId, one.getId());
        List<InspectionItem> inspectionItems = inspectionItemMapper.selectList(wrapper);
        // 封装vo
        InspectionDetailVo<OrderInItem> vo = new InspectionDetailVo<>();
        vo.setOrderDetail(data);
        vo.setInspectionItems(inspectionItems);
        return Result.success(vo, "查询成功");
    }

    /**
     * 修改质检状态
     *
     * @param remark
     * @param inspectionNo
     * @param status
     * @return
     */
    private boolean updateInspectionStatus(String remark, String inspectionNo, QualityStatusEnums status) {
        return this.lambdaUpdate().eq(Inspection::getInspectionNo, inspectionNo)
                .set(Inspection::getStatus, status.getCode())
                .set(Inspection::getInspectionTime, LocalDateTime.now())
                .set(Inspection::getUpdateTime, LocalDateTime.now())
                .set(Inspection::getRemark, remark).update();
    }
}
