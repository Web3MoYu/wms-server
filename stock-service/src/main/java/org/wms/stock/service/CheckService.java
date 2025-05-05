package org.wms.stock.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.stock.model.dto.AddCheckDto;
import org.wms.stock.model.dto.CheckQueryDto;
import org.wms.stock.model.dto.StockCheckDto;
import org.wms.stock.model.entity.Check;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.stock.model.vo.CheckItemVo;
import org.wms.stock.model.vo.CheckVo;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【stock_check(盘点单表)】的数据库操作Service
 * @createDate 2025-05-05 09:16:05
 */
public interface CheckService extends IService<Check> {

    /**
     * 分页查询库存盘点信息
     *
     * @param dto 查询条件
     * @return 分页数据
     */
    Page<CheckVo> pageList(CheckQueryDto dto);

    /**
     * 新增盘点信息
     *
     * @param dto 盘点信息
     * @return 是否成功
     */
    String addCheck(AddCheckDto dto);

    /**
     * 查看盘点详情
     *
     * @param id 盘点ID
     * @return 详情信息
     */
    List<CheckItemVo> detail(String id);

    /**
     * 开始盘点
     *
     * @param dto 盘点信息
     * @return 是否成功
     */
    String startCheck(List<StockCheckDto> dto);

    /**
     * 废弃盘点
     *
     * @param id 盘点ID
     * @return 是否成功
     */
    String cancelCheck(String id);
}
