package com.liangzc.example.sharding_jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DataSourceUtils {

    private static final String host = "172.20.10.10";

    private static final int port = 3306;

    private static final String userName = "root";

    private static final String password = "lzc123456";


    public static DataSource createDataSource(String schemaName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", host, port, schemaName));
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

}
