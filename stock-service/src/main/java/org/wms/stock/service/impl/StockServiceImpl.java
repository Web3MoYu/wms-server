package org.wms.stock.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wms.api.client.LocationClient;
import org.wms.api.client.ProductClient;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.product.Product;
import org.wms.common.model.Result;
import org.wms.common.model.vo.LocationVo;
import org.wms.stock.mapper.StockMapper;
import org.wms.stock.model.dto.StockDto;
import org.wms.stock.model.entity.Stock;
import org.wms.stock.model.vo.StockVo;
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
        if (Objects.isNull(dto.getAscSortByProdDate()) || dto.getAscSortByProdDate()) {
            wrapper.orderByAsc(Stock::getProductionDate);
        } else {
            wrapper.orderByDesc(Stock::getProductionDate);
        }
        Page<Stock> stockPage = this.page(new Page<>(dto.getPage(), dto.getPageSize()), wrapper);

        // 转换为StockVo
        Page<StockVo> stockVoPage = new Page<>(stockPage.getCurrent(), stockPage.getSize(), stockPage.getTotal());
        List<StockVo> stockVoList = stockPage.getRecords().stream().map(stock -> {
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
                    LocationVo locationVo = locationClient.getLocations(stock.getLocation().get(0));
                    if (locationVo != null) {
                        List<LocationVo> locationVoList = new java.util.ArrayList<>();
                        locationVoList.add(locationVo);
                        stockVo.setLocationVo(locationVoList);
                    }
                } catch (Exception e) {
                    // 记录错误日志但不中断流程
                    log.error("获取位置信息失败：{}", stock.getId(), e);
                }
            }

            return stockVo;
        }).collect(java.util.stream.Collectors.toList());
        stockVoPage.setRecords(stockVoList);

        return Result.success(stockVoPage, "查询成功");
    }
}




