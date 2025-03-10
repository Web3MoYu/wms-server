package org.wms.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wms.common.handler.LocationTypeHandler;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * MyBatisPlus配置类
 */
@Configuration
@ConditionalOnClass(SqlSessionFactory.class)
public class MyBatisPlusConfig {

    /**
     * MyBatisPlus拦截器（用于分页）
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加MySQL的分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 注册自定义TypeHandler
     */
    @Bean
    public LocationTypeHandler locationTypeHandler() {
        return new LocationTypeHandler();
    }
}