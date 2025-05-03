package org.wms.stock.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.wms.api.client.UserClient;
import org.wms.common.entity.stock.Stock;
import org.wms.common.entity.sys.User;
import org.wms.common.exception.BizException;
import org.wms.common.model.vo.StockVo;
import org.wms.stock.model.dto.AlertQueryDto;
import org.wms.common.entity.stock.Alert;
import org.wms.stock.model.vo.AlertVo;
import org.wms.stock.service.AlertService;
import org.wms.stock.mapper.AlertMapper;
import org.springframework.stereotype.Service;
import org.wms.stock.service.StockService;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【stock_alert(产品库存预警记录表)】的数据库操作Service实现
 * @createDate 2025-05-02 19:07:21
 */
@Service
public class AlertServiceImpl extends ServiceImpl<AlertMapper, Alert>
        implements AlertService {
    @Resource
    UserClient userClient;

    @Resource
    StockService stockService;

    @Override
    public Page<AlertVo> pageList(AlertQueryDto dto) {
        // 构造条件
        Page<Alert> page = this.lambdaQuery()
                .eq(dto.getAlertType() != null, Alert::getAlertType, dto.getAlertType())
                .ge(dto.getStartDate() != null, Alert::getAlertTime, dto.getStartDate())
                .le(dto.getEndDate() != null, Alert::getAlertTime, dto.getEndDate())
                .eq(dto.getIsHandled() != null, Alert::getIsHandled, dto.getIsHandled())
                .eq(StringUtils.hasText(dto.getHandler()), Alert::getHandler, dto.getHandler())
                .like(StringUtils.hasText(dto.getAlertNo()), Alert::getAlertNo, dto.getAlertNo())
                .orderByDesc(Alert::getAlertTime)
                .page(new Page<>(dto.getPage(), dto.getPageSize()));

        // 转化AlertVo
        List<AlertVo> list = page.getRecords().stream().map((item) -> {
            AlertVo vo = new AlertVo();
            BeanUtils.copyProperties(item, vo);
            Stock stock = stockService.getById(item.getStockId());
            StockVo stockVo = stockService.convertToStockVo(stock);
            User userById = userClient.getUserById(item.getHandler());
            vo.setStock(stockVo);
            vo.setHandlerUser(userById);
            return vo;
        }).toList();

        Page<AlertVo> res = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        res.setRecords(list);
        return res;
    }

    @Override
    public String updateAlertConfig(Stock stock) {
        boolean update = stockService.lambdaUpdate()
                .eq(Stock::getId, stock.getId())
                .set(Stock::getMinStock, stock.getMinStock())
                .set(Stock::getMaxStock, stock.getMaxStock())
                .update();
        if (!update) {
            throw new BizException("修改失败");
        }

        return "修改成功";
    }
}




