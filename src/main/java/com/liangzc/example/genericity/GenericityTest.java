package com.liangzc.example.genericity;

public class GenericityTest<T> {

    T obj;

    public GenericityTest(T obj) {
        this.obj = obj;
    }

    public T print(){

        return obj;
    }

    public void type(){
        System.out.println("type of T is:"+obj.getClass().getName());


    }


    public static void main(String[] args) {

        GenericityTest<Integer> genericityTest = new GenericityTest<Integer>(10);
        System.out.println(genericityTest.print());
        genericityTest.type();
    }
}
