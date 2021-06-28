package com.liangzc.example.spring_demo.aop.xml_way;

import com.liangzc.example.spring_demo.aop.xml_way.basic.service.Animal;
import com.liangzc.example.spring_demo.aop.xml_way.basic.service.CommonService;
import com.liangzc.example.spring_demo.aop.xml_way.basic.service.impl.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopXmlTest {

    public static void main(String[] args) {

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext_XML.xml");

       /* Animal dog = (Animal) app.getBean("dog_xml");

        dog.hobby();*/


        CommonService commonService = (CommonService) app.getBean("commonService");
        commonService.savePersonInfo();
    }
}
