package com.liangzc.example.regex;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

    public static void main(String[] args) {

        String temp = "合同#{companyName}-一次性#{resourceName}-app#{custTel}-客户#{contractStartDate}\n" +
                "合同\n" +
                "到期\n" +
                "一次性\n" +
                "前 2 01:00\n" +
                "多种\n" +
                "多种\n" +
                "app\n" +
                "合同客户\n";

//        test(temp);
        test1();
    }


    public static void test(String temp) {
        List<String> paramKeyList = Lists.newArrayList();
        Pattern p = Pattern.compile("#\\{(\\w*)\\}");
        Matcher m = p.matcher(temp);
        StringJoiner stringJoiner = new StringJoiner(",");
        while (m.find()) {
//            paramKeyList.add(m.group(1));
            paramKeyList.add(m.group());
            System.out.println("group:" + m.group());
            System.out.println("group1:" + m.group(1));
            stringJoiner.add(m.group());
        }

        String joins = stringJoiner.toString();
        System.out.println("joins替换前:" + joins);
        String replaceAll = joins.replaceAll("#\\{companyName}", "CXSD-11111")
                .replaceAll("#\\{resourceName}", "车位1")
                .replaceAll("#\\{custTel}", "18136520321")
                .replaceAll("#\\{contractStartDate}", "2022-07-25 18-24-20");
        System.out.println("joins替换后:" + replaceAll);
        System.out.println(Arrays.asList(replaceAll));

        System.out.println(paramKeyList);
    }

    public static void test1() {
        String temp = "#{prestoreBalanceFee}#{custName}#{resInstName}#{custId}#{endTime}";
        Pattern p = Pattern.compile("#\\{(\\w*)\\}");
        Matcher m = p.matcher(temp);
        StringJoiner stringJoiner = new StringJoiner(",");
        while (m.find()) {
            stringJoiner.add(m.group());
        }
        String joinString = stringJoiner.toString();
        System.out.println("joinString=====================:" + joinString);
        StringJoiner sendStr = new StringJoiner(",");

    }
}
