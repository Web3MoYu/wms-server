package org.wms.stock.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.stock.model.dto.AddMovementDto;
import org.wms.stock.model.dto.MovementDto;
import org.wms.stock.model.entity.Movement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.stock.model.vo.MovementVo;

/**
 * @author moyu
 * @description 针对表【stock_movement(库位变动记录表)】的数据库操作Service
 * @createDate 2025-05-03 22:29:31
 */
public interface MovementService extends IService<Movement> {

    /**
     * 分页查询库位移动信息
     *
     * @param dto 查询条件
     * @return 分页数据
     */
    Page<MovementVo> pageMovement(MovementDto dto);

    /**
     * 新增库存移动
     *
     * @param dto 库位移动信息
     * @return 是否成功
     */
    String addMovement(AddMovementDto dto);

    /**
     * 审批通过
     *
     * @param id 变更ID
     * @return 是否成功
     */
    String approveMovement(String id);

    /**
     * 审批拒绝
     *
     * @param id     变更ID
     * @param reason 原因
     * @return 是否成功
     */
    String rejectMovement(String id, String reason);

    String doneMovement(String id);
}
