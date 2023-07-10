package com.liangzc.example.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapTest {

    public static void main(String[] args) {

        Map map = new HashMap();
        map.put(1, 1);

        ConcurrentMap concurrentMap = new ConcurrentHashMap();
        concurrentMap.put("1", 1);


    }
}
