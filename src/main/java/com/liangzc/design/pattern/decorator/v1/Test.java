package com.liangzc.design.pattern.decorator.v1;
/**
 * 测试类
 * 这是V1版本的包装器测试类，使用继承的方式。不断的进行包装累加
 * 缺点：也看到缺点了，就是继承关系，如果要添加新的包装，需要继承新的类，类层次结构越深，越复杂
 */
public class Test {

    public static void main(String[] args) {
        BaseCake cake = new BaseCake();
        System.out.println(cake.msg() + ", 价格：" + cake.price());

        FruitCake fruitCake = new FruitCake();
        System.out.println(fruitCake.msg() + ", 价格：" + fruitCake.price());

        ChocolateAndFruitCake chocolateCake = new ChocolateAndFruitCake();
        System.out.println(chocolateCake.msg() + ", 价格：" + chocolateCake.price());


    }
}
