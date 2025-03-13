package org.wms.order.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import org.wms.order.model.enums.OrderType;

/**
 * 订单编号生成工具类
 */
@Component
public class OrderNoGenerator {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 订单类型前缀
    private static final Map<Integer, String> ORDER_PREFIX = Map.of(
            0, "IR", // 入库订单 Inbound Receipt
            1, "OR"  // 出库订单 Outbound Receipt
    );

    // 日期格式
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 生成订单编号
     * 格式：前缀 + 日期 + 6位序列号
     * 例如：IR2025031000001
     *
     * @param orderType 订单类型
     * @return 订单编号
     */
    public String generateOrderNo(OrderType orderType) {
        String prefix = ORDER_PREFIX.getOrDefault(orderType.getCode(), "OT"); // 默认其他类型
        String date = LocalDate.now().format(DATE_FORMATTER);
        String key = "order:no:" + prefix + ":" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（2天）
        redisTemplate.expire(key, 2, TimeUnit.DAYS);

        // 格式化为6位序列号，不足补0
        String sequenceStr = String.format("%06d", sequence);

        return prefix + date + sequenceStr;
    }
} 