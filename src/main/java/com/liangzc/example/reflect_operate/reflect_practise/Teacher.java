package com.liangzc.example.reflect_operate.reflect_practise;

public class Teacher {

    private String name;

    int age;

    public String adress;


    public Teacher() {

    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Teacher(String name, int age, String adress) {
        this.name = name;
        this.age = age;
        this.adress = adress;
    }


    public void method1(){
        System.out.println("这是一个public访问类型的方法!");
    }


    private void method2(String param){
        System.out.println("这是一个private访问类型的方法!"+param);
    }


    public String method3(){
        System.out.println("带返回值的public访问类型的方法！");
        return "带返回值的public访问类型的方法！";
    }

    void method4(){
        System.out.println("默认的访问类型的方法！");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", adress='" + adress + '\'' +
                '}';
    }
}
