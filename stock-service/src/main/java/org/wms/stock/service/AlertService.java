package org.wms.stock.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.stock.model.dto.AlertQueryDto;
import org.wms.stock.model.entity.Alert;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.stock.model.vo.AlertVo;

/**
 * @author moyu
 * @description 针对表【stock_alert(产品库存预警记录表)】的数据库操作Service
 * @createDate 2025-05-02 19:07:21
 */
public interface AlertService extends IService<Alert> {

    /**
     * 根据查询条件分页查询预警信息
     *
     * @param dto 查询条件
     * @return 预警的分页信息
     */
    Page<AlertVo> pageList(AlertQueryDto dto);
}
