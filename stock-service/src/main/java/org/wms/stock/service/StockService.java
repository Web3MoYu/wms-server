package org.wms.stock.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.wms.common.model.Result;
import org.wms.stock.model.dto.StockDto;
import org.wms.stock.model.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.stock.model.vo.StockVo;

/**
 * @author moyu
 * @description 针对表【stock(库存表)】的数据库操作Service
 * @createDate 2025-03-10 22:19:37
 */
public interface StockService extends IService<Stock> {

    /**
     * 分页查询库存
     *
     * @param page              当前页码
     * @param pageSize          每页条数
     * @param productId         商品ID
     * @param areaId            区域ID
     * @param shelfId           货架ID
     * @param status            状态
     * @param batchNumber       批次号
     * @param ascSortByProdDate 是否按生产日期升序排序
     * @return 库存分页结果
     */
    Result<Page<StockVo>> pageStocks(StockDto dto);
}
