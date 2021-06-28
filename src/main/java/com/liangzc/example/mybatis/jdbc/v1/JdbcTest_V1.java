package com.liangzc.example.mybatis.jdbc.v1;
import com.liangzc.example.mybatis.jdbc.Person;

import java.sql.*;

public class JdbcTest_V1 {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        query();
        update();
    }

    private static void update() throws SQLException, ClassNotFoundException {
        //加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");

        //建立数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://119.23.189.136:3306/own?characterEncoding=utf-8&serverTimezone=UTC", "root", "lzc123456");

        Statement st = connection.createStatement();

        String sql = "insert into person(name,age,gender) values ('lili','20','女')";
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            count += st.executeUpdate(sql);
        }
        long end = System.currentTimeMillis();
        System.out.println("================================耗时："+ (end - start));

        if(count > 0){
            System.out.println("插入数据成功！");
        }
    }

    static void query() throws ClassNotFoundException, SQLException {
        //加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");

        //建立数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://119.23.189.136:3306/own?characterEncoding=utf-8&serverTimezone=UTC", "root", "lzc123456");

        Statement statement = connection.createStatement();

        String sql = "select * from person";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){

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

        if(rs != null){
            rs.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
