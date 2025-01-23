package com.liangzc.example;

import java.util.*;
import java.util.stream.Collectors;

public class columnExample {

    public static void main(String[] args) {


        List<Integer> rows = Arrays.asList(1, 2, 3, 4, 5);
        List<FeeItemType> column = new ArrayList<>();

        for (int i = 1000; i < 1002; i++) {
            FeeItemType feeItemType1 = new FeeItemType(i + "", "电费", "实收本年");
            FeeItemType feeItemType2 = new FeeItemType(i + "", "电费", "实收往年年");
            FeeItemType feeItemType3 = new FeeItemType(i + "", "电费", "预收及以后");
            column.add(feeItemType1);
            column.add(feeItemType2);
            column.add(feeItemType3);
        }


        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, List<FeeItemType>> collect = column.stream().collect(Collectors.groupingBy(FeeItemType::getFeeItemTypeId));

        List<FeeItemType> feeItemTypes = new ArrayList<>();
        for (Map.Entry<String, List<FeeItemType>> stringListEntry : collect.entrySet()) {
            stringListEntry.getValue().add(new FeeItemType(stringListEntry.getValue().get(0).getFeeItemTypeId(), stringListEntry.getValue().get(0).getFeeItemTypeName(), "合计"));
            feeItemTypes = stringListEntry.getValue();
        }

        for (FeeItemType feeItemType : feeItemTypes) {
            System.out.println(feeItemType);
        }


    }
}
