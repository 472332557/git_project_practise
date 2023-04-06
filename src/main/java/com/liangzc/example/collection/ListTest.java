package com.liangzc.example.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {

        List<BallSports> ballSports = new ArrayList<>();
        List<BallSports> ballSportsList = new LinkedList<>();
        BallSports ballSports1 = new BallSports(1,"足球","1");
        BallSports ballSports2 = new BallSports(2,"篮球","1");
        BallSports ballSports3 = new BallSports(3,"羽毛球","2");

        //ArrayList
        ballSports.add(ballSports1);
        ballSports.add(ballSports2);
        ballSports.add(ballSports3);

        //LinkedList
        ballSportsList.add(ballSports1);
        ballSportsList.add(ballSports2);
        ballSportsList.add(ballSports3);
    }



    //arrayList中使用到的api
    @Test
    public void listAddSummary(){
        //返回两个数中的最大值
        System.out.println("最大值："+Math.max(1, 2));
        Object[] obgArray = {1,2,3,4,5};
        /**
         * Arrays.copyOf
         * 返回一个新的数组对象，新数组对象包含原数组数据;
         * 第二个变量指定建立的新数组的大小。
         */
        Object[] objects = Arrays.copyOf(obgArray, 10);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        System.out.println(objects.length);

    }
}
