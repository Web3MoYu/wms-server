package org.wms.common.utils;

import java.time.LocalDateTime;

public class TimeUtils {

    /**
     * 根据时间范围获取开始时间
     *
     * @param range 时间范围
     * @return 开始时间
     */
    public static LocalDateTime getStartTime(String range) {
        LocalDateTime now = LocalDateTime.now();
        return switch (range) {
            case "1day" -> now.minusDays(1);
            case "1week" -> now.minusWeeks(1);
            case "1month" -> now.minusMonths(1);
            case "3months" -> now.minusMonths(3);
            case "6months" -> now.minusMonths(6);
            default -> throw new IllegalArgumentException("Invalid range: " + range);
        };
    }
}
