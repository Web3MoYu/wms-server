package org.wms.common.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.wms.common.exception.BizException;
import org.wms.common.model.Location;

import java.util.List;

public class StockLocationTypeHandler extends AbstractJsonTypeHandler<List<Location>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public StockLocationTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    public List<Location> parse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new BizException("转换Json失败");
        }
    }

    @Override
    public String toJson(List<Location> obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BizException("转换Json失败");
        }
    }
}
