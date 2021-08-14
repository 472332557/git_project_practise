package com.liangzc.example.sharding_jdbc;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingDatabaseAndTableConfiguration {


    private static Map<String, DataSource> createDataSource(){
        Map<String, DataSource> sourceMap = new HashMap<>();
        sourceMap.put("ds0", DataSourceUtils.createDataSource("shard_01"));
        sourceMap.put("ds1", DataSourceUtils.createDataSource("shard_02"));
        return sourceMap;
    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration(){

        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();

        //把逻辑表和真实表的对应关系添加到分片规则配置中
        configuration.getTables().add(getOrderTableRuleConfiguration());

        //设置数据库分库规则，根据user_id进行分库操作
        configuration.setDefaultDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration(
                        "user_id", "db-lzc-inline"));
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", "ds${user_id%2}");

        //设置分库策略
        configuration.getShardingAlgorithms()
                .put("db-lzc-inline", new ShardingSphereAlgorithmConfiguration("INLINE", properties));

        //设置表的分片规则(数据的水平拆分)
        configuration.setDefaultTableShardingStrategy(
                new StandardShardingStrategyConfiguration(
                        "order_id", "order-inline"));

        //设置分表策略
        Properties properties1 = new Properties();
        properties1.setProperty("algorithm-expression", "t_order_${order_id%2}");
        configuration.getShardingAlgorithms()
                .put("order-inline",
                        new ShardingSphereAlgorithmConfiguration("INLINE", properties1));

        //设置主键生成策略
        // * UUID
        // * 雪花算法
        Properties properties2 = new Properties();
        properties2.setProperty("worker-id", "123");
        configuration.getKeyGenerators()
                .put("snowflake",
                        new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", properties2));
        return configuration;
    }

    //配置逻辑表及表的id策略
    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration(){
        ShardingTableRuleConfiguration ruleConfiguration =
                new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");
        ruleConfiguration.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        return ruleConfiguration;
    }

    public static DataSource getShardingDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.
                createDataSource(createDataSource(),
                        Collections.singleton(createShardingRuleConfiguration()),
                        new Properties());
    }

}
