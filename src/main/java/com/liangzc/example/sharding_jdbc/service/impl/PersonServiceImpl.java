package com.liangzc.example.sharding_jdbc.service.impl;

import com.liangzc.example.sharding_jdbc.entity.Order;
import com.liangzc.example.sharding_jdbc.service.PersonService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonServiceImpl implements PersonService {

    private DataSource dataSource;
    Random random = new Random();

    public PersonServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, address_id BIGINT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id))";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    @Override
    public Long insert(Order order) throws SQLException {
        String sql = "INSERT INTO t_order (user_id, address_id, status) VALUES (?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, order.getUserId());
        preparedStatement.setLong(2, order.getAddressId());
        preparedStatement.setString(3, order.getStatus());
        preparedStatement.executeUpdate();
        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                order.setOrderId(resultSet.getLong(1));
            }
        }
        return order.getOrderId();
    }

    public List<Long> insertOrder() throws SQLException {
        List<Long> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Order order = createOrder(i);
            Long insert = insert(order);
            result.add(insert);
        }
        return result;
    }


    private Order createOrder(final int i) {
        Order order = new Order();
        order.setUserId(random.nextInt(10000));
        order.setAddressId(i);
        order.setStatus("INSERT_TEST");
        return order;
    }
}

