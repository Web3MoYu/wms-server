package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.OrderOut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wms.order.model.vo.OrderStatisticsVo;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【order_out(出库订单表)】的数据库操作Mapper
 * @createDate 2025-03-12 20:41:27
 * @Entity org.wms.order.model.entity.OrderOut
 */
@Mapper
public interface OrderOutMapper extends BaseMapper<OrderOut> {

    /**
     * 获取订单统计信息
     *
     * @return 统计信息
     */
    List<OrderStatisticsVo> getOrderStatistics(String startStr, String endStr);
}




