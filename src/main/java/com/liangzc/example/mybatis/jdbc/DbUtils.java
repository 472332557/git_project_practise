package com.liangzc.example.mybatis.jdbc;

import java.sql.*;

public class DbUtils {


    private static final String url = "jdbc:mysql://119.23.189.136:3306/own?characterEncoding=utf-8&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String name = "root";
    private static final String password = "lzc123456";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获得连接
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, name, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void close(Connection connection) {
        close(connection, null);
    }

    public static void close(Connection connection, Statement statement) {

        close(connection, statement, null);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
