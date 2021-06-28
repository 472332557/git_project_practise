package com.liangzc.example.mybatis.cache;

import com.alibaba.fastjson.JSONObject;
import com.liangzc.example.mybatis.jdbc.DbUtils;
import org.apache.ibatis.cache.Cache;

import java.sql.*;

public class PersonCacheV1 implements Cache {

    private String id;
    private Connection connection = DbUtils.getConnection();
    public PersonCacheV1(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("-------------------添加进缓存-----------------------------");
        PreparedStatement preparedStatement = null;
        String sql = "insert into person_cache(cache_key,cache_value) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, key.toString());
            preparedStatement.setObject(2,value.toString());
            int i = preparedStatement.executeUpdate();
            if(i > 0){
                System.out.println("cache add SCUUESS");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement);
        }
    }


    @Override
    public Object getObject(Object key) {
        System.out.println("-----------------从缓存获取-----------------");
        String sql = "select * from person_cache where cache_key = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1,key.toString());
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                int id = rst.getInt("id");
                String cache_key = rst.getString("cache_key");
                String cache_value = rst.getString("cache_value");
                JSONObject object = new JSONObject();
                object.put("id", id);
                object.put("cache_key", cache_key);
                object.put("cache_value", cache_value);
                System.out.println(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("-----------------从缓存删除-----------------");
        PreparedStatement preparedStatement = null;
        String sql = "delete from person_cache where cahche_key = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, key.toString());
            int i = preparedStatement.executeUpdate();
            if(i > 0){
                System.out.println("cache delete SCUUESS");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.close(connection,preparedStatement);
        }
        return null;
    }

    @Override
    public void clear() {
        System.out.println("-----------------从缓存删除-----------------");
        Statement statement = null;
        String sql = "delete from person_cache";
        try {
            statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            if(i > 0){
                System.out.println("cache delete SCUUESS");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.close(connection,statement);
        }
    }

    @Override
    public int getSize() {
        return 0;
    }
}
