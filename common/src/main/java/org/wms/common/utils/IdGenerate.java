package org.wms.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.wms.common.enums.inspect.InspectType;
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

    // 质检类型前缀
    private static final Map<Integer, String> INSPECT_PREFIX = Map.of(
            1, "QI", // 入库质检 Quality Inspection Inbound
            2, "QO", // 出库质检 Quality Inspection Outbound
            3, "QS"  // 库存质检 Quality Inspection Stock
    );

    // 拣货单前缀
    private static final String PICKING_PREFIX = "PO"; // 拣货单 Picking Order

    // 预警前缀
    private static final String ALERT_PREFIX = "AL";

    // 库位移动前缀
    private static final String STORAGE_MOVEMENT_PREFIX = "SM"; // 库位移动 Storage Movement

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

    /**
     * 生成质检编号
     * 格式：前缀 + 日期 + 6位序列号
     * 例如：QI2025031000001（入库质检）
     * QO2025031000001（出库质检）
     * QS2025031000001（库存质检）
     *
     * @param inspectType 质检类型
     * @return 质检编号
     */
    public String generateInspectionNo(InspectType inspectType) {
        String prefix = INSPECT_PREFIX.getOrDefault(inspectType.getCode(), "QX"); // 默认其他类型
        String date = LocalDate.now().format(DATE_FORMATTER);
        String key = "inspect:no:" + prefix + ":" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（2天）
        redisTemplate.expire(key, 2, TimeUnit.DAYS);

        // 格式化为6位序列号，不足补0
        String sequenceStr = String.format("%06d", sequence);

        return prefix + date + sequenceStr;
    }

    /**
     * 生成拣货编号
     * 格式：前缀 + 日期 + 6位序列号
     * 例如：PO2025031000001
     *
     * @return 拣货编号
     */
    public String generatePickingNo() {
        String prefix = PICKING_PREFIX;
        String date = LocalDate.now().format(DATE_FORMATTER);
        String key = "picking:no:" + prefix + ":" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（2天）
        redisTemplate.expire(key, 2, TimeUnit.DAYS);

        // 格式化为6位序列号，不足补0
        String sequenceStr = String.format("%06d", sequence);

        return prefix + date + sequenceStr;
    }

    /**
     * 生成预警编号
     * 格式：前缀 + 日期 + 6位序列号
     * 例如：AL2025031000001
     *
     * @return 预警编号
     */
    public String generateAlertNo() {
        String prefix = ALERT_PREFIX;
        String date = LocalDate.now().format(DATE_FORMATTER);
        String key = "alert:no:" + prefix + ":" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（7天）
        redisTemplate.expire(key, 7, TimeUnit.DAYS);

        // 格式化为6位序列号，不足补0
        String sequenceStr = String.format("%06d", sequence);

        return prefix + date + sequenceStr;
    }

    /**
     * 生成库位移动编号
     * 格式：前缀 + 日期 + 6位序列号
     * 例如：SM2025031000001
     *
     * @return 库位移动编号
     */
    public String generateStorageMovementNo() {
        String prefix = STORAGE_MOVEMENT_PREFIX;
        String date = LocalDate.now().format(DATE_FORMATTER);
        String key = "movement:no:" + prefix + ":" + date;

        // 使用Redis获取自增序列号
        Long sequence = redisTemplate.opsForValue().increment(key);
        // 设置过期时间（3天）
        redisTemplate.expire(key, 3, TimeUnit.DAYS);

        // 格式化为6位序列号，不足补0
        String sequenceStr = String.format("%06d", sequence);

        return prefix + date + sequenceStr;
    }
} 