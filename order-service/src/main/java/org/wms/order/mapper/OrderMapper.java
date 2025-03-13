package org.wms.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.vo.OrderVo;

/**
 * 订单统一查询Mapper
 */
@Mapper
public interface OrderMapper {

    /**
     * 联合查询入库和出库订单
     *
     * @param query  查询条件
     * @param offset 偏移量
     * @param limit  限制数
     * @return 订单VO列表
     */
    List<OrderVo> unionQueryOrders(
            @Param("query") OrderQueryDto query,
            @Param("offset") int offset,
            @Param("limit") int limit);

    /**
     * 统计联合查询的总记录数
     *
     * @param query 查询条件
     * @return 总记录数
     */
    Long countUnionQueryOrders(@Param("query") OrderQueryDto query);
} 