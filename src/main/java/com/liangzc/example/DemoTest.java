package com.liangzc.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class DemoTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoTest.class);

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

        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void forTest(){

        for (int i = 0; i < 10; i++) {
            System.out.println("i当前数："+i);

            for (int j = 0; j < 10; j++) {
                System.out.println("j当前数："+j);
                if (j == 5){
                    System.out.println("=====跳出======："+j);
                    break;
                }
            }

            System.out.println("-----------------i结束："+i);
        }
    }


    @Test
    public void replaceTest(){
        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));
    }

    @Test
    public void StingConcatTest(){
        String rootPath = "";
        String rootPath2 = null;
        System.out.println(rootPath.concat("fileName").concat(".xlsx"));
        System.out.println(rootPath2.concat("fileName").concat(".xlsx"));

    }

    @Test
    public void logErrorTest(){
        for (int i = 10; i >= 0; i--) {
            try {
                if(i == 6){
                    throw new Exception("111111");
                }
                System.out.println(i);
            }catch (Exception e){
                logger.error("处理数据出错,值为：{}",i,e);
//                continue;
            }
        }

    }

    @Test
    public void sizeTest(){
        System.out.println(Integer.SIZE);
    }

    @Test
    public void fileTest(){

        String fileName = "D:/receive-file/DOWNLOAD_PATH/在线支付查询_20221121155215693199.xlsx";
        File file = new File(fileName);

        System.out.println("文件名："+fileName);
        System.out.println("后缀下标位置："+fileName.indexOf("."));
        System.out.println("文件名长度："+fileName.length());
        System.out.println("文件是否存在:"+file.exists());
        if(!file.exists()){
            fileName = fileName.substring(0, fileName.indexOf(".")).concat(".xls");
            System.out.println("新文件名："+fileName);
            file = new File(fileName);
            System.out.println("文件是否存在"+file.exists());
        }


    }

    @Test
    public void addTest(){

        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    @Test
    public void mathTest(){

        //Math.ceil:向上取整
        System.out.println((int)Math.ceil((double) 10 / (double) 3));

        //向下取整
        System.out.println(Math.floor((double) 10 / (double) 3));

    }

    @Test
    public void futureTest() throws InterruptedException {

            CompletableFuture.runAsync(() -> {
                try {
                    calcTest();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        TimeUnit.SECONDS.sleep(1);

    }

    private void calcTest() {

        for (int i = 0; i < 10; i++) {
            try {
                int i2 = i % 2;
                System.out.println(i+"对2取模后的值："+i2);
                if(i2 == 0){
                    throw new RuntimeException("错误："+i);
                }
                System.out.println(i);
            }catch (Exception e){
                logger.error("错误呀！",e);
//                System.out.println("错误呀！,"+e.getMessage());
            }
        }
    }

    @Test
    public void randomTest(){

        System.out.println((int)((Math.random()*9+1)*100000));


        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date()));
    }

    @Test
    public void qumo(){

        System.out.println(800 % 500);

    }

    @Test
    public void dateTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = "20230615";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(date));
//        calendar.add(Calendar.DATE,1);
//        Date time = calendar.getTime();

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String start = dateFormat.format(calendar.getTime());
        System.out.println(start);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String end = dateFormat.format(calendar.getTime());
        System.out.println(end);


//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);
//        System.out.println(calendar.get(Calendar.DATE));

        System.out.println("---------------------------------------------------");
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        String beginStr = "20230601000000";
        String endStr = "20230630000000";

        System.out.println(beginStr.substring(0, 8).equals(start));
        System.out.println(endStr.substring(0, 8).equals(end));

    }

    @Test
    public void objTest(){
        String resp = "{\"code\":\"0\",\"message\":\"success\",\"data\":{}}";
        System.out.println(resp);
        JSONObject object = JSON.parseObject(resp);
        String code = null, msg = null, result = null;
        if (object.containsKey("code")) {
            code = object.getString("code");
        }
        if (object.containsKey("message")) {
            msg = object.getString("message");
        }
        if (object.containsKey("data")) {
            result = object.getString("data");
        }
        System.out.println(object);

        System.out.println("result:"+result);
        System.out.println(result != null);
        System.out.println(result.length());

        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject);
        System.out.println(jsonObject != null);
        System.out.println(jsonObject.size());

        ApartmentCalcBo apartmentCalcBo = JSON.parseObject(result, ApartmentCalcBo.class);


        System.out.println(apartmentCalcBo);

        System.out.println(apartmentCalcBo.getBillData() != null);

        System.out.println(System.currentTimeMillis());
    }


    @Test
    public void jsonTest(){
        String result = "{\"code\":\"0\",\"msg\":\"\",\"data\":{\"contractChangeDto\":{\"contractId\":11522,\"contractNo\":\"ZGDX-2018-08-0013\",\"totalRentArea\":\"81.6\",\"secondId\":1032,\"secondName\":\"深圳市汇嘉粮食有限公司\",\"secondType\":\"企业客户\",\"secondCredType\":\"营业执照\",\"secondCredNo\":\"000000000\",\"contractResource\":2001,\"startDate\":20180101000000,\"endDate\":20201231000000,\"depositMoney\":1349600,\"preTaxAmount\":1929400,\"afterTaxAmount\":1929400,\"taxAmount\":0,\"firstLease\":612000,\"averagePrice\":0,\"processTimes\":\"2018-12-03 16:02:18\",\"contractDuration\":36,\"resList\":[]}},\"message\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(result);
        Object obj = jsonObject.getJSONObject("data").get("contractChangeDto");
        ContractDetaiDto contractDetaiDto = JSON.parseObject(obj.toString(), ContractDetaiDto.class);

        System.out.println(contractDetaiDto);
    }

    @Test
    public void equalsTest(){

        Long id = 1002L;
        String s = String.valueOf(id);
        String ids = "1002";

        System.out.println(s.equals(ids));

    }

    @Test
    public void numFormat(){

        String fee = "-3287.00";
        BigDecimal bigDecimal = new BigDecimal(fee);
        Long feelong = bigDecimal.longValue();
        System.out.println(bigDecimal.doubleValue());

        Long abs = Math.abs(feelong);

        System.out.println(feelong);
        System.out.println(abs);
        String s = abs.toString();
        System.out.println("string s :"+s);

//        System.out.println(Long.valueOf(fee));

    }

    @Test
    public void bigDecimalTest(){
        BigDecimal fee = new BigDecimal("870968.00");
        BigDecimal taxFee = new BigDecimal("41474.6667");

        System.out.println(fee.subtract(taxFee).setScale(0,BigDecimal.ROUND_HALF_UP).longValue());

        BigDecimal feeNotax = BigDecimal.ZERO;

        for (int i = 0; i < 3; i++) {
            feeNotax = feeNotax.add(new BigDecimal(100.3465));
            System.out.println(feeNotax);
        }

        System.out.println(feeNotax.setScale(0,BigDecimal.ROUND_HALF_UP));
    }

}
