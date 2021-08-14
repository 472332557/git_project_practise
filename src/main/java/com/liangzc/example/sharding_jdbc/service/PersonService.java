package com.liangzc.example.sharding_jdbc.service;

import com.liangzc.example.sharding_jdbc.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {

    public void init() throws SQLException;

    public Long insert(Order order) throws SQLException;

    public List<Long> insertOrder() throws SQLException;
}
