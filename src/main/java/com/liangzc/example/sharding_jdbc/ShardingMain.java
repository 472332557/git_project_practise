package com.liangzc.example.sharding_jdbc;

import com.liangzc.example.sharding_jdbc.service.PersonService;
import com.liangzc.example.sharding_jdbc.service.impl.PersonServiceImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ShardingMain {

    public static void main(String[] args) throws SQLException {

        DataSource dataSource = ShardingDatabaseAndTableConfiguration.getShardingDataSource();
        PersonService personService = new PersonServiceImpl(dataSource);

        personService.init();
        personService.insertOrder();

    }
}
