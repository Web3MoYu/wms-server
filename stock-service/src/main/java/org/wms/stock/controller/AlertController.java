package org.wms.stock.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wms.common.entity.stock.Alert;
import org.wms.common.entity.stock.Stock;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;
import org.wms.security.util.SecurityUtil;
import org.wms.stock.model.dto.AlertQueryDto;
import org.wms.stock.model.vo.AlertVo;
import org.wms.stock.service.AlertService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/stock/alert")
public class AlertController {

    @Resource
    AlertService alertService;

    /**
     * 根据查询条件分页查询预警信息
     *
     * @param dto 查询条件
     * @return 预警的分页信息
     */
    @PostMapping("/pages")
    @PreAuthorize("hasAuthority('inventory:alert:list')")
    public Result<Page<AlertVo>> pages(@RequestBody AlertQueryDto dto) {
        return Result.success(alertService.pageList(dto), "查询成功");
    }

    /**
     * 修改预警配置
     *
     * @param stock 参数
     * @return 是否修改成功
     */
    @PutMapping("/updateAlertConfig")
    @PreAuthorize("isAuthenticated()")
    public Result<String> updateAlertConfig(@RequestBody Stock stock) {
        return Result.success(alertService.updateAlertConfig(stock), "修改预警配置成功");
    }

    /**
     * 处理预警信息
     *
     * @param id 预警ID
     * @return 是否处理成功
     */
    @PutMapping("/handle/{id}")
    public Result<String> handleAlert(@PathVariable String id) {
        // 判断有无权限
        Alert alert = alertService.getById(id);
        String userId = SecurityUtil.getUserID();
        if (!StrUtil.equals(alert.getHandler(), userId)) {
            throw new BizException("无权限");
        }
        // 修改状态
        alert.setIsHandled(1);
        alert.setHandlingTime(LocalDateTime.now());
        alert.setUpdateTime(LocalDateTime.now());
        boolean b = alertService.updateById(alert);
        if (!b) {
            throw new BizException("处理预警信息失败");
        }
        return Result.success(null, "处理成功");
    }
}
