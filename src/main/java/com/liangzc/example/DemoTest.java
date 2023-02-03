package com.liangzc.example;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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


}