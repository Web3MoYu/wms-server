package org.wms.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.order.model.entity.PickingOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wms.order.model.vo.PickingStatisticsVo;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【picking_order(拣货单表)】的数据库操作Mapper
 * @createDate 2025-04-12 14:41:26
 * @Entity org.wms.order.model.entity.PickingOrder
 */
@Mapper
public interface PickingOrderMapper extends BaseMapper<PickingOrder> {

    /**
     * 获取订单统计信息
     *
     * @return 统计信息
     */
    List<PickingStatisticsVo> getOrderStatistics(String startStr, String endStr);
}




