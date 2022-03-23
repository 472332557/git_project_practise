package com.liangzc.example.genericity.genericmethod;

public class TestDemo {

    public static void main(String[] args) {

        //虽然是泛型类，但是却并没有使用到泛型,所以变量是Object类型
        GenericPet genericPet = new GenericPet();

        genericPet.setName("柯基");
        //未使用泛型，所以返回类型也是Objectleixng
        Object name = genericPet.getName();
        genericPet.print();

        System.out.println("----------------------------------------");

        GenericPet<String> stringGenericPet = new GenericPet<>();
        stringGenericPet.setName("布偶");
        //使用了泛型指定类型：String，所以返回类型也是String
        String name1 = stringGenericPet.getName();
        stringGenericPet.print();

        System.out.println("----------------------泛型方法----------------------");
        stringGenericPet.say("边牧");
        stringGenericPet.say(5);

        //可以看到，虽然泛型类的类型和泛型方法的类型是一样的，此时泛型类类型是：String，但是泛型方法依然可以是Integer类型，所以泛型方法和泛型类是互不影响的
        stringGenericPet.speak(1);


    }
}
