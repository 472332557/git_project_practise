package com.liangzc.example.mybatis;

import com.github.pagehelper.PageHelper;
import com.liangzc.example.mybatis.model.Person;
import com.liangzc.example.mybatis.service.PersonService;
import com.liangzc.example.web_start.WebController;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringTest {


    @Autowired
    private PersonService personService;

    @Autowired
    private WebController controller;


    @Test
    public void test1() {

//        RowBounds rowBounds = new RowBounds(2, 2);
        PageHelper.offsetPage(4, 2);
        List<Person> people = personService.selectPersonByGender(null);
        System.out.println(people);
    }


    @Test
    public void test2() {

        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.singleTest();
        }

    }
}
