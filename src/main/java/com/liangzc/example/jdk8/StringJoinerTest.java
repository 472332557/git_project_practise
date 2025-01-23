package com.liangzc.example.jdk8;

import java.util.StringJoiner;

/**
 * 拼接字符使用java8提供的StringJoiner测试
 */
public class StringJoinerTest {

    public static void main(String[] args) {

//        StringJoiner joiner = new StringJoiner("|");
        StringJoiner joiner = new StringJoiner("|", "{", "}");

        String[] stringArry = {"1", "2", "3", "4", "5", "dsdsfdsfds"};

        for (String s : stringArry) {
            joiner.add(s);
        }

        System.out.println(joiner.toString());

    }
}
