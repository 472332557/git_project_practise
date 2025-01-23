package com.liangzc.example.spring_demo.aop.xml_way.basic.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.liangzc.example.spring_demo.aop.xml_way.basic.service.CommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommonServiceImpl implements CommonService {

    @Override
    public int savePersonInfo() {

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext_XML.xml");
        DruidDataSource dataSource = (DruidDataSource) app.getBean("dataSource");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        int update = jdbcTemplate.update("insert into person(id,name,age,gender) values (default ,'aop',20,'女')");
        if (update > 0) {
            System.out.println("SUCCESS-------------------------");
        }
        return update;
    }
}
