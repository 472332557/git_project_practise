package com.liangzc.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    }
}
