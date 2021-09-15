package com.liangzc.example.mybatis.mapper;

import com.liangzc.example.mybatis.model.Person;
import com.liangzc.example.mybatis.model.PersonCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapper{
    int countByExample(PersonCriteria example);

    int deleteByExample(PersonCriteria example);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExampleWithRowbounds(PersonCriteria example, RowBounds rowBounds);

    List<Person> selectByExample(PersonCriteria example);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonCriteria example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonCriteria example);

    List<Person> selectPersonList(@Param("name") String name, @Param("id") Integer id);

    List<Person> selectPersonByGender(RowBounds rowBounds);
}