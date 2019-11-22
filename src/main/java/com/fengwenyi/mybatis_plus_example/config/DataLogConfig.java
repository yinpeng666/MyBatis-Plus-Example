package com.fengwenyi.mybatis_plus_example.config;

import com.fengwenyi.mybatis_plus_example.controller.DataLogHandler;
import com.fengwenyi.mybatis_plus_example.interceptor.DataUpdateInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**
 * 数据更新日志处理配置（实现按需加载）
 *
 * @author Tophua
 * @date 2019/8/2
 */
//@Configuration
//@AllArgsConstructor
//@ConditionalOnBean({DataSource.class, DataLogHandler.class})
public class DataLogConfig implements WebMvcConfigurer {

    /*private final DataLogHandler dataLogHandler;
    private final DataSource dataSource;

    @Bean
    @ConditionalOnMissingBean
    public DataUpdateInterceptor getDataUpdateInterceptor() {
        return new DataUpdateInterceptor(dataSource, dataLogHandler);
    }*/

}
