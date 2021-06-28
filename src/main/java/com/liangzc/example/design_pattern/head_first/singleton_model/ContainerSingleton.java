package com.liangzc.example.design_pattern.head_first.singleton_model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring借鉴了枚举式单例，进行了优化，形成了一个容器式单例，解决了大批量创建对象
 * 但是会有线程安全问题
 */
public class ContainerSingleton {

    private static ContainerSingleton instance;

    private ContainerSingleton(){}

    static Map<String, Object> containerMap = new ConcurrentHashMap<>();

    public static Object getInstance(String name){
        if(!containerMap.containsKey(name)){
            try {
                Object o =  Class.forName(name).newInstance();
                containerMap.put(name, o);
            }catch (Exception e){
                e.printStackTrace();
            }
            return containerMap.get(name);
        }else {
            return containerMap.get(name);
        }
    }
}
