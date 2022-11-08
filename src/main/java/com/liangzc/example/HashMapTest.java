package com.liangzc.example;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    public static void main(String[] args) {


        String bankCode = " 1000 2003 0050 3156 2014 ";

        System.out.println(bankCode);
        System.out.println(bankCode.trim().replaceAll("\\s",""));

        List<String> list = new ArrayList<>();
        list.add("1");

        System.out.println(Math.max(10, 1));

        /**
         *  1000  0100
         *  0111
         *
         *  1100  0110
         */
        int a = 8 >> 1;
        System.out.println(a);

        Map<String, Object> map = new HashMap<>();
        Object put = map.put("111", 1111);
        System.out.println(put);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", 1);

        System.out.println("hash码为："+("1".hashCode()));

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1", 1);


    }

    @Test
    public void multiValueMapTest(){
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("1", 111);
        multiValueMap.add("1", 222);
        multiValueMap.add("1", 333);
        System.out.println(multiValueMap);
        System.out.println("--------------------------------------------------------------------");
        Map<String, Object> linkedMap = new LinkedHashMap<>();
        linkedMap.put("1", 111);
        linkedMap.put("1", 222);
        linkedMap.put("1", 333);
        System.out.println(linkedMap);
        System.out.println("--------------------------------------------------------------------");
        Map<String, Object> map = new HashMap<>();
        map.put("1", 111);
        map.put("1", 222);
        map.put("1", 333);
        System.out.println(map);
    }
}
