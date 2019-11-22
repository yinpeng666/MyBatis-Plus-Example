package com.fengwenyi.mybatis_plus_example;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fengwenyi.mybatis_plus_example.config.DataLogConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.fengwenyi.mybatis_plus_example.dao")
public class MybatisPlusExampleApplication {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    @Bean
//    public DataLogConfig dataLogConfig(){
//        return new DataLogConfig();
//    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusExampleApplication.class, args);
    }

}
