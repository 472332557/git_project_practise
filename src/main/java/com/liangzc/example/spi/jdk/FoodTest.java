package com.liangzc.example.spi.jdk;

import com.liangzc.example.spi.Food;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class FoodTest {

    @Test
    public void FoodTest(){

        //jdk  加载配置文件配置实例

        ServiceLoader<Food> serviceLoader = ServiceLoader.load(Food.class);
        Iterator<Food> iterator = serviceLoader.iterator();

        while (iterator.hasNext()){
            Food next = iterator.next();
            next.printName();
        }
    }
}
