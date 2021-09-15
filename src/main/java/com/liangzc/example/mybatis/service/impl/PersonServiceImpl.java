package com.liangzc.example.mybatis.service.impl;

import com.liangzc.example.mybatis.mapper.PersonMapper;
import com.liangzc.example.mybatis.model.Person;
import com.liangzc.example.mybatis.service.PersonService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;


    @Override
    public List<Person> selectPersonByGender(RowBounds rowBounds) {
        return personMapper.selectPersonByGender(rowBounds);
    }
}
