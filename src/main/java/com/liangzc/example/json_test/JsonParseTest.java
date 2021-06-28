package com.liangzc.example.json_test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonParseTest {

    public static void main(String[] args) {

        String array = "[{acctItemId=80564029, transferFee=2, transferPaidFee=0}, {acctItemId=80564030, transferFee=1, transferPaidFee=0}, {acctItemId=80564031, transferFee=1, transferPaidFee=0}, {acctItemId=80564032, transferFee=1, transferPaidFee=0}, {acctItemId=80564033, transferFee=4, transferPaidFee=0}, {acctItemId=80564034, transferFee=4, transferPaidFee=0}, {acctItemId=80564035, transferFee=12, transferPaidFee=0}, {acctItemId=80564036, transferFee=18, transferPaidFee=0}, {acctItemId=80564037, transferFee=176, transferPaidFee=0}, {acctItemId=80564038, transferFee=87, transferPaidFee=0}, {acctItemId=80564039, transferFee=39, transferPaidFee=0}, {acctItemId=80564040, transferFee=10, transferPaidFee=0}, {acctItemId=80564041, transferFee=20, transferPaidFee=0}, {acctItemId=80564042, transferFee=81, transferPaidFee=0}, {acctItemId=80564043, transferFee=68, transferPaidFee=0}, {acctItemId=80564044, transferFee=262, transferPaidFee=0}, {acctItemId=80564045, transferFee=2, transferPaidFee=0}, {acctItemId=80564046, transferFee=75, transferPaidFee=0}, {acctItemId=80564047, transferFee=85, transferPaidFee=0}, {acctItemId=80564048, transferFee=65, transferPaidFee=0}, {acctItemId=80564049, transferFee=101, transferPaidFee=0}, {acctItemId=80551719, transferFee=88866, transferPaidFee=0}, {acctItemId=80551720, transferFee=14047, transferPaidFee=0}]";


        JSONArray jsonArray = JSON.parseArray(array);
        System.out.println(jsonArray);
    }
}
