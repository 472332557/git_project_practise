package com.liangzc.example.mybatis.jdbc.v2;

import com.liangzc.example.mybatis.jdbc.DbUtils;
import com.liangzc.example.mybatis.jdbc.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest_v2 {

    public static void main(String[] args) throws SQLException {
//        insertBatch();
        query();
    }


    static void insertBatch() throws SQLException {

        Connection connection = DbUtils.getConnection();
        String sql = "insert into person(name,age,gender) values (?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pst.setObject(1, "小美");
            pst.setObject(2, 18);
            pst.setObject(3, "女");
            pst.addBatch();
        }
        int[] ints = pst.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("耗时========================：" + (end - start));
        DbUtils.close(connection, pst);
        if (ints.length > 0) {
            System.out.println("SUCCESS");
        }
    }


    static void query() {
        Connection connection = DbUtils.getConnection();
        String sql = "select * from person where name = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1, "丽丽");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");

                    Person person = new Person();
                    person.setId(id);
                    person.setName(name);
                    person.setAge(age);
                    person.setGender(gender);
                    System.out.println(person);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }

    }
}
