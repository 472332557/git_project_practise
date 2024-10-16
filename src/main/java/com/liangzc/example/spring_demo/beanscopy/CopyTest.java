package com.liangzc.example.spring_demo.beanscopy;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CopyTest {

    /**
     * Spring的copyProperties如果字段类型不一样，则不会复制
     * apache 的copyProperties，字段类型不一样，也会复制
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void copy() throws InvocationTargetException, IllegalAccessException {

        Fruit fruit = new Fruit();
        fruit.setId(1);
        fruit.setName("茄子");
        fruit.setAddress("武威");
        fruit.setMoney(10000);
        System.out.println(fruit);
        System.out.println("-------------------------spring copy--------------------------------");
        FruitCopy fruitCopy = new FruitCopy();
        BeanUtils.copyProperties(fruit, fruitCopy);
        System.out.println("spring copy后：" + fruitCopy);

        System.out.println("-----------------------------apache copy----------------------------------");
        FruitCopy fruitCopy1 = new FruitCopy();
        org.apache.commons.beanutils.BeanUtils.copyProperties(fruitCopy1, fruit);
        System.out.println("apache copy后：" + fruitCopy1);


    }
}
