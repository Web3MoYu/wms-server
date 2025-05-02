package org.wms.stock.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.common.model.vo.CountVo;
import org.wms.stock.model.dto.StockDto;
import org.wms.common.entity.stock.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.vo.StockCountVo;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【stock(库存表)】的数据库操作Service
 * @createDate 2025-03-10 22:19:37
 */
public interface StockService extends IService<Stock> {

    /**
     * 分页查询库存
     *
     * @return 库存分页结果
     */
    Result<Page<StockVo>> pageStocks(StockDto dto);

    /**
     * 新增库存信息
     *
     * @param stock 库存
     * @return 结果
     */
    Result<String> addStock(Stock stock);


    /**
     * 修改库存信息
     *
     * @param stock 库存
     * @return 结果
     */
    Result<String> updateStock(Stock stock);

    /**
     * 根据 批次号 和 商品id 查询库存并转化为vo
     *
     * @param batchNumber 批次号
     * @param productId   商品id
     * @return vo
     */
    StockVo getStockByBatchAndProduct(String batchNumber, String productId);

    /**
     * 统计库存数量
     *
     * @return 库存数量
     */
    Result<List<StockCountVo>> countStock();

    /**
     * 将Stock转换为StockVo
     * @param stock stock
     * @return vo
     */
    StockVo convertToStockVo(Stock stock);
}
