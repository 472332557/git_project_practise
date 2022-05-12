package com.liangzc.example.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JDBCTemplateConfig {


    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("lzc123456");
        druidDataSource.setUrl("jdbc:mysql://119.23.189.136:3306/own?characterEncoding=utf-8&serverTimezone=UTC&rewriteBatchedStatements=true");
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
