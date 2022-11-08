package com.liangzc.example.spi.spring;

import com.liangzc.example.spi.Food;
import org.junit.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

public class SpringTest {

    @Test
    public void springTest(){

        //1、
        List<String> loadFactoryNames = SpringFactoriesLoader.loadFactoryNames(Food.class, ClassUtils.getDefaultClassLoader());

        for (String loadFactoryName : loadFactoryNames) {

            System.out.println(loadFactoryName);
        }

    }

    @Test
    public void springTest2(){
        List<Food> foods = SpringFactoriesLoader.loadFactories(Food.class, ClassUtils.getDefaultClassLoader());
        for (Food food : foods) {
            System.out.println(food);
            food.printName();
        }
    }
}
