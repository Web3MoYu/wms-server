package org.wms.stock.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wms.api.client.LocationClient;
import org.wms.api.client.ProductClient;
import org.wms.common.entity.product.Product;
import org.wms.common.enums.location.LocationStatusEnums;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;
import org.wms.common.model.Result;
import org.wms.common.model.vo.CountVo;
import org.wms.common.model.vo.LocationVo;
import org.wms.stock.mapper.StockMapper;
import org.wms.stock.model.dto.StockDto;
import org.wms.common.entity.stock.Stock;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.vo.StockCountVo;
import org.wms.stock.service.StockService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【stock(库存表)】的数据库操作Service实现
 * @createDate 2025-03-10 22:19:37
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
        implements StockService {

    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    @Resource
    LocationClient locationClient;

    @Resource
    ProductClient productClient;


    @Override
    public Result<Page<StockVo>> pageStocks(StockDto dto) {
        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(dto.getProductId()), Stock::getProductId, dto.getProductId())
                .eq(StringUtils.isNotBlank(dto.getAreaId()), Stock::getAreaId, dto.getAreaId())
                .eq(Objects.nonNull(dto.getStatus()), Stock::getAlertStatus, dto.getStatus())
                .like(StringUtils.isNotBlank(dto.getBatchNumber()), Stock::getBatchNumber, dto.getBatchNumber());
        if (Objects.nonNull(dto.getAscSortByProdDate()) && dto.getAscSortByProdDate()) {
            wrapper.orderByAsc(Stock::getProductionDate);
        }
        if (Objects.nonNull(dto.getAscSortByProdDate()) && !dto.getAscSortByProdDate()) {
            wrapper.orderByDesc(Stock::getProductionDate);
        }
        if (Objects.nonNull(dto.getAscSortByAvailableQuantity()) && dto.getAscSortByAvailableQuantity()) {
            wrapper.orderByAsc(Stock::getAvailableQuantity);
        }
        if (Objects.nonNull(dto.getAscSortByAvailableQuantity()) && !dto.getAscSortByAvailableQuantity()) {
            wrapper.orderByDesc(Stock::getAvailableQuantity);
        }
        if (Objects.nonNull(dto.getAscSortByQuantity()) && dto.getAscSortByQuantity()) {
            wrapper.orderByAsc(Stock::getQuantity);
        }
        if (Objects.nonNull(dto.getAscSortByQuantity()) && !dto.getAscSortByQuantity()) {
            wrapper.orderByDesc(Stock::getQuantity);
        }


        Page<Stock> stockPage = this.page(new Page<>(dto.getPage(), dto.getPageSize()), wrapper);

        // 转换为StockVo
        Page<StockVo> stockVoPage = new Page<>(stockPage.getCurrent(), stockPage.getSize(), stockPage.getTotal());
        List<StockVo> stockVoList = stockPage.getRecords().stream().map(this::convertToStockVo).collect(java.util.stream.Collectors.toList());
        stockVoPage.setRecords(stockVoList);

        return Result.success(stockVoPage, "查询成功");
    }

    @Override
    public Result<String> addStock(Stock stock) {
        // 根据batchNumber和productId查询库存
        Stock one = this.lambdaQuery()
                .eq(Stock::getProductId, stock.getProductId())
                .eq(Stock::getBatchNumber, stock.getBatchNumber()).one();

        // TODO 预警
        // 新增
        boolean success = false;
        if (Objects.isNull(one)) {
            stock.setCreateTime(LocalDate.now());
            stock.setUpdateTime(LocalDate.now());
            success = this.save(stock);
        } else {
            // 修改已有库存
            // 首先将原先的状态修改为空闲
            List<Location> list = one.getLocation();
            for (Location location : list) {
                boolean b = locationClient.updateStatusInStorage(location,
                        LocationStatusEnums.FREE.getCode(), one.getProductId());
                if (!b) {
                    throw new BizException("新增库存失败");
                }
            }
            stock.setUpdateTime(LocalDate.now());
            stock.setId(one.getId());
            success = this.updateById(stock);

        }
        // 更新货架状态为占用
        List<Location> list = stock.getLocation();
        for (Location location : list) {
            boolean b = locationClient.updateStatusInStorage(location, LocationStatusEnums.OCCUPIED.getCode(), stock.getProductId());
            if (!b) {
                throw new BizException("新增库存失败");
            }
        }

        if (!success) {
            throw new BizException("新增库存失败");
        }
        return Result.success(null, "新增库存成功");

    }

    @Override
    public Result<String> updateStock(Stock stock) {
        // 查询原有信息
        // TODO 预警
        // 修改其余属性
        boolean b = this.updateById(stock);
        if (!b) {
            throw new BizException("修改库存失败");
        }
        return Result.success(null, "修改成功");
    }

    @Override
    public StockVo getStockByBatchAndProduct(String batchNumber, String productId) {
        Stock one = this.lambdaQuery()
                .eq(Stock::getBatchNumber, batchNumber)
                .eq(Stock::getProductId, productId)
                .one();
        if (Objects.isNull(one)) {
            return null;
        }
        return convertToStockVo(one);
    }

    @Override
    public Result<List<StockCountVo>> countStock() {
        List<Stock> list = this.list();
        List<StockCountVo> res = list.stream().map((item) -> {
            StockCountVo vo = new StockCountVo();
            // 获取商品名称
            Product product = productClient.getProductById(item.getProductId());
            vo.setName(product.getProductName());
            // 统计同一种商品其余批次的数量和批次号
            List<Stock> stocks = lambdaQuery().eq(Stock::getProductId, item.getProductId()).list();
            List<CountVo> countVos = stocks.stream().map((stock -> {
                CountVo countVo = new CountVo();
                countVo.setName(stock.getBatchNumber());
                countVo.setCount(stock.getQuantity());
                return countVo;
            })).toList();
            vo.setBatchCount(countVos);
            return vo;
        }).toList();
        return Result.success(res, "查询成功");
    }

    /**
     * 将stock转化为Vo
     *
     * @param stock 库存对象
     * @return vo
     */
    private StockVo convertToStockVo(Stock stock) {
        StockVo stockVo = new StockVo();
        BeanUtils.copyProperties(stock, stockVo);

        // 获取产品名称
        if (StringUtils.isNotBlank(stock.getProductId())) {
            try {
                Product product = productClient.getProductById(stock.getProductId());
                if (product != null) {
                    stockVo.setProductName(product.getProductName());
                } else {
                    stockVo.setProductName("未知产品");
                }
            } catch (Exception e) {
                // 记录错误日志但不中断流程
                log.error("获取产品信息失败：{}", stock.getProductId(), e);
                stockVo.setProductName("未知产品");
            }
        }

        // 获取区域名称
        if (StringUtils.isNotBlank(stock.getAreaId())) {
            try {
                String areaName = locationClient.getArea(stock.getAreaId()).getAreaName();
                stockVo.setAreaName(Objects.requireNonNullElse(areaName, "未知区域"));
            } catch (Exception e) {
                // 记录错误日志但不中断流程
                log.error("获取区域信息失败：{}", stock.getAreaId(), e);
                stockVo.setAreaName("未知区域");
            }
        }

        // 获取位置信息
        if (stock.getLocation() != null && !stock.getLocation().isEmpty()) {
            try {
                List<LocationVo> list = new ArrayList<>();
                for (Location location : stock.getLocation()) {
                    LocationVo vo = locationClient.getLocations(location);
                    list.add(vo);
                }
                stockVo.setLocationVo(list);
            } catch (Exception e) {
                // 记录错误日志但不中断流程
                log.error("获取位置信息失败：{}", stock.getId(), e);
            }
        }

        return stockVo;
    }


}




