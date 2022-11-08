package com.liangzc.example.jdk8.lambda.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PetTest {




    @Test
    public void getName(){

        Pet pet = new Pet() {
            @Override
            public void getName(String name) {
                System.out.println(name);
            }
        };

        pet.getName("小");
    }


    @Test
    public void lambdaTest(){
        Pet pet = (e) -> System.out.printf(e);
        pet.getName("xixo");
    }

    List<Integer> nums = Arrays.asList(1,2,310,15,25);

    @Test
    public void streamTest(){
        nums.stream().filter(e -> e > 10).forEach(System.out::println);
    }


    //处理字符串
    public String strHandler(String str,StrExample example){
        return example.getValue(str);
    }

    @Test
    public void getStr(){
        String strHandler = strHandler("abcdefg", new StrExample() {
            @Override
            public String getValue(String name) {
                return name.toUpperCase();
            }
        });
        System.out.println(strHandler);
    }

    @Test
    public void getStrByLambda(){
        String dd = strHandler("abdfsfg", e -> e.toUpperCase());
        System.out.println(dd);
    }


}
