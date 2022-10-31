package com.liangzc.example;

import org.junit.Test;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DemoTest {

    public static void main(String[] args) {


        System.out.println(1 << 30);


        System.out.println(128 >>>3);
        System.out.println(128 >>3);


        List list = new LinkedList();

        System.out.println("--------------------------------------");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));


        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));

    }

    public static String tempReplace(String temp){
        return temp.replaceAll("#\\{organName}", "优家园").replaceAll("#\\{resInstName}", "lllll");
    }

    @Test
    public void subStringTest(){
        String date = "20220908000000";
        System.out.println(date.substring(0,8));
    }

    @Test
    public void forTest(){

        for (int i = 0; i < 10; i++) {
            System.out.println("当前数："+i);

            if (i == 5){
                System.out.println("=====跳出======："+i);
                return;
            }
            System.out.println("-----------------结束："+i);
        }
    }


    @Test
    public void replaceTest(){
        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));
    }



}
