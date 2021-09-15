package com.liangzc.example.mybatis.service;

import com.liangzc.example.mybatis.model.Person;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PersonService {

    List<Person> selectPersonByGender(RowBounds rowBounds);
}
