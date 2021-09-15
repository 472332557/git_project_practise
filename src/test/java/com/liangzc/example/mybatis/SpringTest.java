package com.liangzc.example.mybatis;

import com.liangzc.example.mybatis.model.Person;
import com.liangzc.example.mybatis.service.PersonService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringTest {


    @Autowired
    private PersonService personService;


    @Test
    public void test1(){

        RowBounds rowBounds = new RowBounds(0, 2);
        List<Person> people = personService.selectPersonByGender(rowBounds);
        System.out.println(people);
    }
}
