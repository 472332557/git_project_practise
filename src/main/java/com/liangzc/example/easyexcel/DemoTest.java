package com.liangzc.example.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 阿里巴巴easyExcel文档参考：https://easyexcel.opensource.alibaba.com/
 */
public class DemoTest {

    public static String filePath = "D:/receive-file/DOWNLOAD_PATH";


    //简单导出
    @Test
    public void simpleTest(){

        String fileName = filePath + "/easyExcel_" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板1").doWrite(()-> data());

    }

    //仅导出指定列或者忽略指定列
    @Test
    public void appointExport(){
        String fileName = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";

        //根据传入字段，忽略 DemoData中对应的date列
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("date");
        EasyExcel.write(fileName,DemoData.class).excludeColumnFieldNames(excludeColumnFiledNames).sheet("指定忽略").doWrite(data());


        String fileName1 = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        // 根据用户传入字段 假设我们只要导出 DemoData中对应的date列,项目中汇总列的，可以使用这种方式
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("date");
        EasyExcel.write(fileName1,DemoData.class).includeColumnFieldNames(excludeColumnFiledNames).sheet("指定包含").doWrite(data());
    }

    //多次写入到同一个sheet
    @Test
    public void duplicationExportSheet(){
        /**
         *  1、写到同一个sheet
         */
        String fileName = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        try(ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("重复写入sheet模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        }
    }

    /**
     * 动态头、动态列写入
     * @return
     */
    @Test
    public void dynamicHeadExport(){
        String fileName = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        // 根据用户传入字段 假设我们只要导出 DemoData中对应的date列,项目中汇总列的，可以使用这种方式
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("date");
        EasyExcel.write(fileName).head(head()).sheet("动态头sheet").doWrite(dynamicData());

    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<String>> dynamicData() {
        List<List<String>> list = new ArrayList<List<String>>();

        for (int i = 0; i < 100; i++) {
            List<String> head0 = new ArrayList<String>();
            head0.add("动态列1_"+i);
            head0.add("动态列2_"+i);
            head0.add("动态列3_"+i);
            list.add(head0);
        }

//        List<String> head1 = new ArrayList<String>();
//        head1.add("动态列2" + System.currentTimeMillis());
//        head1.add("动态列2" + System.currentTimeMillis());
//        head1.add("动态列2" + System.currentTimeMillis());
//        List<String> head2 = new ArrayList<String>();
//        head2.add("动态列3" + System.currentTimeMillis());
//        head2.add("动态列3" + System.currentTimeMillis());
//        head2.add("动态列3" + System.currentTimeMillis());

//        list.add(head1);
//        list.add(head2);
        return list;
    }


    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setName("列名"+i);
            data.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            data.setNumber(2673);
            list.add(data);
        }
        return list;
    }
}
