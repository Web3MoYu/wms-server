package org.wms.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.wms.common.enums.order.OrderType;

import jakarta.annotation.Resource;

/**
 * 订单编号生成工具类
 */
@ConditionalOnClass(RedisTemplate.class)
public class IdGenerate {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 订单类型前缀
    private static final Map<Integer, String> ORDER_PREFIX = Map.of(
            0, "IR", // 入库订单 Inbound Receipt
            1, "OR"  // 出库订单 Outbound Receipt
    );

    // 日期格式
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    // 批次日期格式
    private static final DateTimeFormatter BATCH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

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

    /**
     * 生成批次号
     * 格式：日期 + 8位序列号
     * 例如：2025031000000001
     *
     * @return 批次号
     */
    public String generateBatchNo() {
        String date = LocalDate.now().format(BATCH_DATE_FORMATTER);
        String key = "batch:no:global:" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（1个月）
        redisTemplate.expire(key, 31, TimeUnit.DAYS);

        // 格式化为8位序列号，不足补0
        String sequenceStr = String.format("%08d", sequence);

        return date + sequenceStr;
    }
} 