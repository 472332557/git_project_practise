package com.liangzc.example.mybatis;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.liangzc.example.mybatis.mapper.PersonMapper;
import com.liangzc.example.mybatis.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test1 {


    @Test
    public void test1() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取defaultSqlSessionFactory对象，并且解析配置文件、映射文件和statement
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session对象，并且初始化了Execute对象
        SqlSession sqlSession = sessionFactory.openSession();

        sqlSession.update("");

        PageHelper.offsetPage(0,2);
        List<Person> personList = sqlSession.selectList("com.liangzc.example.mybatis.mapper.PersonMapper.selectPersonList");

        for (Person person : personList) {
            System.out.println(person);
        }

        System.out.println("========================再次查询=============================");
        personList = sqlSession.selectList("com.liangzc.example.mybatis.mapper.PersonMapper.selectPersonList");
        for (Person person : personList) {
            System.out.println(person);
        }

        sqlSession.close();
    }


    @Test
    public void test2() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取defaultSqlSessionFactory对象，并且解析配置文件、映射文件和statement
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session对象defaultSqlSession，并且初始化了Execute对象
        SqlSession sqlSession = sessionFactory.openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> personList = mapper.selectPersonList("玛丽",1);
        for (Person person : personList) {
            System.out.println(person);
        }
        sqlSession.close();
    }

    @Test
    public void queryWithPage() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取defaultSqlSessionFactory对象，并且解析配置文件、映射文件和statement
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session对象defaultSqlSession，并且初始化了Execute对象
        SqlSession sqlSession = sessionFactory.openSession();

        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        RowBounds rowBounds = new RowBounds(0,2);

        List<Person> personList = personMapper.selectPersonByGender(rowBounds);

        for (Person person : personList) {
            System.out.println(person);
        }
        sqlSession.close();

    }


//    @Test
    public void insert() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取defaultSqlSessionFactory对象，并且解析配置文件、映射文件和statement
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session对象，并且初始化了Execute对象
        SqlSession sqlSession = sessionFactory.openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person personModel = new Person();
        List<String> nameList = new ArrayList<>();
        nameList.add("丽丽");
        personModel.setName(nameList);
        personModel.setAge(20);
        personModel.setGender("女");
        int insert = mapper.insert(personModel);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testDynamic() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取defaultSqlSessionFactory对象，并且解析配置文件、映射文件和statement
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取session对象defaultSqlSession，并且初始化了Execute对象
        SqlSession sqlSession = sessionFactory.openSession();

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person personDto = new Person();
        personDto.setName(Lists.newArrayList("丽丽"));
        personDto.setGender("1");
        List<Person> personList = mapper.selectByPersonObj(personDto);
        for (Person person : personList) {
            System.out.println(person);
        }
        sqlSession.close();
    }


}
