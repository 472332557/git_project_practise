package com.liangzc.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaDemo {

    public static void main(String[] args) {

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("key", i);
            list.add(map);
        }

        List<String> key = list.stream().map(map -> map.get("key").toString()).collect(Collectors.toList());
        for (String s : key) {
            System.out.println(s);
        }

    }
}
