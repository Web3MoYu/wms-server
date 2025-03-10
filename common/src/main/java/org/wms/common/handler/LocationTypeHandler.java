package org.wms.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wms.common.model.Location;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理List<Location>类型与JSON之间的转换
 */
@MappedTypes(List.class)
public class LocationTypeHandler extends BaseTypeHandler<List<Location>> {
    private static final Logger logger = LoggerFactory.getLogger(LocationTypeHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Location> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            logger.error("转换List<Location>为JSON失败", e);
            throw new SQLException("转换List<Location>为JSON失败", e);
        }
    }

    @Override
    public List<Location> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJSON(rs.getString(columnName));
    }

    @Override
    public List<Location> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJSON(rs.getString(columnIndex));
    }

    @Override
    public List<Location> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJSON(cs.getString(columnIndex));
    }

    private List<Location> parseJSON(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            return objectMapper.readValue(json, new TypeReference<List<Location>>() {});
        } catch (JsonProcessingException e) {
            logger.error("解析JSON为List<Location>失败", e);
            return new ArrayList<>();
        }
    }
} 