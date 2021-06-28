package com.liangzc.example.design_pattern.head_first.singleton_model;

import java.lang.reflect.Constructor;

public class ReflectTest {

    public static void main(String[] args) {

        Class<InteriorClassSingleton> singletonClass = InteriorClassSingleton.class;

        try {
            Constructor<InteriorClassSingleton> declaredConstructor = singletonClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            InteriorClassSingleton classSingleton1 = declaredConstructor.newInstance();

            InteriorClassSingleton classSingleton = declaredConstructor.newInstance();

            System.out.println(classSingleton1);
            System.out.println(classSingleton);
            System.out.println(classSingleton == classSingleton1);

            System.out.println("-----------------------------------------------------------------------------------");
            /**
             * 通过反射获取枚举式单例实例
             * 最终会报：java.lang.IllegalArgumentException: Cannot reflectively create enum objects
             * 不允许通过反射创建枚举对象
             */
            Class<EnumSingleton> enumSingletonClass = EnumSingleton.class;
            Constructor<EnumSingleton> declaredConstructor1 = enumSingletonClass.getDeclaredConstructor(String.class,int.class);
            declaredConstructor1.setAccessible(true);
            EnumSingleton enumSingleton = declaredConstructor1.newInstance();
            System.out.println(enumSingleton);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
