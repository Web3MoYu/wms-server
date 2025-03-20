package org.wms.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转换为json字符串
     * 
     * @param obj 对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转换为对象
     * 
     * @param json      json字符串
     * @param valueType 对象类型
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
