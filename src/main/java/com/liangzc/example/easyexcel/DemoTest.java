package com.liangzc.example.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.excel.write.style.row.SimpleRowHeightStyleStrategy;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("date");
        try(ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).includeColumnFieldNames(includeColumnFiledNames).build()) {
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
     * 动态头、动态列写入，不创建对象的写，复杂头
     * @return
     */
    @Test
    public void dynamicHeadExport(){
        String fileName = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        List<List<String>> lists = dynamicData();
        lists.add(Lists.newArrayList("合计", "合计", "合计", "ddd", "111"));
        System.out.println("lists size:"+lists.size());

        //设置样式

        //设置头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //背景设置为蓝色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setBold(true);
        headWriteFont.setFontHeightInPoints((short) 13);
        headWriteFont.setFontName("宋体");
        headWriteCellStyle.setWriteFont(headWriteFont);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)11);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //头和内容的策略
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);
        //合并单元格，最后一行，第1 - 3列合并
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(lists.size()+1,lists.size()+1,0,2);
        EasyExcel.write(fileName).head(head())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())//自动列宽
                .registerWriteHandler(horizontalCellStyleStrategy)//样式
                .registerWriteHandler(onceAbsoluteMergeStrategy)//合并单元格
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short)35,(short)25))//头行高：40 内容行高：25
                .registerWriteHandler(new CustomCellWriteHandler())//自定义策略
                .sheet("动态头sheet")
                .doWrite(lists);
    }


    /**
     * 合并单元格
     * @return
     */
    @Test
    public void mergExport(){
        String fileName = filePath + "/easyExcel_" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        //第2列，每隔2行进行汇总
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 1);
        EasyExcel.write(fileName).head(head()).registerWriteHandler(loopMergeStrategy).sheet("合并sheet").doWrite(dynamicData());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(Lists.newArrayList("字符串"));
        list.add(Lists.newArrayList("数字"));
        list.add(Lists.newArrayList("日期"));
        list.add(Lists.newArrayList("复杂头","复杂头1"));
        list.add(Lists.newArrayList("复杂头","复杂头2"));
        return list;
    }

    private List<List<String>> dynamicData() {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < 100; i++) {
            List<String> head0 = new ArrayList<String>();
            head0.add("动态列1_"+i);
            head0.add("动态列2_"+i);
            head0.add("动态列3_"+i);
            head0.add("动态列4_复杂1"+i);
            head0.add("动态列4_复杂2"+i);
            list.add(head0);
        }
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

    @Test
    public void ListsArrayList(){

        String[] titles = {"1","2","3","4","5"};
        ArrayList<String> strings = Lists.newArrayList(titles);
        System.out.println(strings);
    }
}
