package com.liangzc.example.jdk8.lambda;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FifterDemo {

    public static void main(String[] args) {

        //创建一个集合
        List<String> list = new ArrayList<>();
        list.add("a1");list.add("a2");list.add("a3");list.add("b1");list.add("b2");list.add("b3");
        sreamFilterTest(list);
    }

    public static void sreamFilterTest(List<String> lists){ //要明确这list的泛型类型，否则jvm不能根据上下文确定参数类型
        lists.stream().filter((s -> s.startsWith("a"))).forEach(System.out::print);//将开头是a的过滤出来
        System.out.println();

        //等价于以上操作
        Predicate<String> predicate = (s) -> s.startsWith("a");//将开头是a的过滤出来
        lists.stream().filter(predicate).forEach(System.out::print);
        System.out.println();

        //连续过滤
        Predicate<String> predicate1 = (s -> s.endsWith("1"));//将开头是a，并且结尾是1的过滤出来
        lists.stream().filter(predicate).filter(predicate1).forEach(System.out::print);
    }


    @Test
    public void filterTest(){
        String objs = "[\n" +
                "  {\n" +
                "    \"buildingName\": \"1栋\",\n" +
                "    \"chargeObjectCode\": \"PLANHOUSE|6031947\",\n" +
                "    \"chargeObjectId\": 6031947,\n" +
                "    \"chargeObjectName\": \"1栋-1101\",\n" +
                "    \"chargeObjectType\": \"PLANHOUSE\",\n" +
                "    \"customerId\": 2041772,\n" +
                "    \"customerName\": \"AA33小李1\",\n" +
                "    \"customerType\": \"OWNER\",\n" +
                "    \"masterOwnerId\": 2041772,\n" +
                "    \"ownerName\": \"AA33小李1\",\n" +
                "    \"ownerStatus\": true,\n" +
                "    \"roomNo\": \"1101\",\n" +
                "    \"spaceName\": \"1101\",\n" +
                "    \"totalPaidAmount\": 8291,\n" +
                "    \"totalPayableAmount\": 8291,\n" +
                "    \"totalPendingAmount\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"advanceBalanceAmount\": 2005,\n" +
                "    \"buildingName\": \"1栋\",\n" +
                "    \"chargeObjectCode\": \"PLANHOUSE|6031948\",\n" +
                "    \"chargeObjectId\": 6031948,\n" +
                "    \"chargeObjectName\": \"1栋-1102\",\n" +
                "    \"chargeObjectType\": \"PLANHOUSE\",\n" +
                "    \"customerId\": 2005625,\n" +
                "    \"customerName\": \"AA33小李2\",\n" +
                "    \"customerType\": \"OWNER\",\n" +
                "    \"masterOwnerId\": 2005625,\n" +
                "    \"ownerName\": \"AA33小李2\",\n" +
                "    \"ownerStatus\": true,\n" +
                "    \"roomNo\": \"1102\",\n" +
                "    \"spaceName\": \"1102\",\n" +
                "    \"totalPaidAmount\": 1551,\n" +
                "    \"totalPayableAmount\": 8339,\n" +
                "    \"totalPendingAmount\": 6788\n" +
                "  },\n" +
                "  {\n" +
                "    \"buildingName\": \"1栋\",\n" +
                "    \"chargeObjectCode\": \"PLANHOUSE|6031949\",\n" +
                "    \"chargeObjectId\": 6031949,\n" +
                "    \"chargeObjectName\": \"1栋-1103\",\n" +
                "    \"chargeObjectType\": \"PLANHOUSE\",\n" +
                "    \"customerId\": 2005649,\n" +
                "    \"customerName\": \"AA33小李3\",\n" +
                "    \"customerType\": \"OWNER\",\n" +
                "    \"masterOwnerId\": 2005649,\n" +
                "    \"ownerName\": \"AA33小李3\",\n" +
                "    \"ownerStatus\": true,\n" +
                "    \"roomNo\": \"1103\",\n" +
                "    \"spaceName\": \"1103\"\n" +
                "  }]";


        List<ReceivableOverviewNormalPageVO> receivableOverviewNormalPageVOS = JSON.parseArray(objs, ReceivableOverviewNormalPageVO.class);
        System.out.println(receivableOverviewNormalPageVOS);

//        receivableOverviewNormalPageVOS = receivableOverviewNormalPageVOS.stream().filter(e -> e.getTotalPaidAmount().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());

        receivableOverviewNormalPageVOS = receivableOverviewNormalPageVOS.stream().filter(e -> Objects.nonNull(e.getTotalPendingAmount())).filter(e->e.getTotalPendingAmount().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
        System.out.println(receivableOverviewNormalPageVOS);
    }
}
