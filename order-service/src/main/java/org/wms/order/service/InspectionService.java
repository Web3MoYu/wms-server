package org.wms.order.service;

import org.wms.common.model.Result;
import org.wms.order.model.dto.InBoundInspectDto;
import org.wms.order.model.dto.InspectionDto;
import org.wms.order.model.dto.StockInDto;
import org.wms.order.model.entity.Inspection;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.vo.InspectionDetailVo;
import org.wms.order.model.vo.InspectionVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【quality_inspection(质检记录表)】的数据库操作Service
 * @createDate 2025-03-21 10:02:29
 */
public interface InspectionService extends IService<Inspection> {

    /**
     * 查询入库订单列表
     *
     * @param dto 查询条件
     * @return 结果
     */
    Page<InspectionVo> pageList(InspectionDto dto);

    /**
     * 入库质检
     *
     * @param dto 质检条件
     * @return 质检结果
     */
    Result<String> inBoundCheck(InBoundInspectDto dto);

    /**
     * 入库质检详情
     *
     * @param id
     * @return 详细信息
     */
    Result<InspectionDetailVo<OrderInItem>> inDetail(String id);

    /**
     * 上架单个商品
     *
     * @param dto 商品信息
     * @return 上架结果
     */
    Result<String> stockOne(StockInDto dto);

    /**
     * 确认上架
     *
     * @param inspectNo 质检编号
     * @return 上架结果
     */
    Result<String> stockAll(String inspectNo);
}
