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
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 阿里巴巴easyExcel文档参考：https://easyexcel.opensource.alibaba.com/
 */
public class DemoTest {

    public static String filePath = "D:/receive-file/DOWNLOAD_PATH";


    //简单导出
    @Test
    public void simpleTest() {

        String fileName = filePath + "/easyExcel_" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(new CustomSheetWriteHandler())
                .sheet("模板1").doWrite(() -> data());

        /*try(ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).registerWriteHandler(new CustomSheetWriteHandler()).build()){
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0,"模板1").build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1,"模板2").build();
            excelWriter.write(data(), writeSheet2);
            excelWriter.write(data(), writeSheet1);

        }*/
    }

    //仅导出指定列或者忽略指定列
    @Test
    public void appointExport() {
        String fileName = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";

        //根据传入字段，忽略 DemoData中对应的date列
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("date");
        EasyExcel.write(fileName, DemoData.class).excludeColumnFieldNames(excludeColumnFiledNames).sheet("指定忽略").doWrite(data());


        String fileName1 = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        // 根据用户传入字段 假设我们只要导出 DemoData中对应的date列,项目中汇总列的，可以使用这种方式
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("date");
        EasyExcel.write(fileName1, DemoData.class).includeColumnFieldNames(excludeColumnFiledNames).sheet("指定包含").doWrite(data());
    }

    //多次写入到同一个sheet
    @Test
    public void duplicationExportSheet() {
        /**
         *  1、写到同一个sheet
         */
        String fileName = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).head(head())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())//自动列宽
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 35, (short) 25)).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("重复写入sheet模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//                List<DemoData> data = data();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                excelWriter.write(dynamicData(), writeSheet);
            }
        }
    }

    /**
     * 多次写入到同一个sheet页的其他写法，像上面的带自动关闭流的try块写法，在实际项目中，设置表头数据及样式时，需要先去获取分页返回的数据，基于这个才能设置
     * 但上面的写法没办法满足，所以只好按下面的写法，try-finally，每次手动去关闭写入的IO流。
     */
    @Test
    public void duplicationExportSheetV2() {
        /**
         *  1、写到同一个sheet
         */
        String fileName = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        boolean isEnter = false;
        ExcelWriter excelWriter = null;
        WriteSheet writeSheet = null;

        int count = 91;
        int headRows = 2;
        int mergerColumnIndex = 1;
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = buildHorizontalCellStyleStrategy();
        try {
            for (int i = 1; i <= 10; i++) {
                if (!isEnter) {
                    //只有超过两个单元格才合并
                    if (mergerColumnIndex > 1) {
                        //合并单元格：合计
                        OnceAbsoluteMergeStrategy mergeStrategy = new OnceAbsoluteMergeStrategy(count + headRows, count + headRows, 0, mergerColumnIndex - 1);
                        excelWriter = EasyExcel.write(fileName).head(head())
                                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())//自动列宽
                                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 35, (short) 25))
                                .registerWriteHandler(mergeStrategy)
                                .registerWriteHandler(horizontalCellStyleStrategy)//样式
                                .registerWriteHandler(new CustomCellWriteHandler(count + headRows))//自定义处理器去处理最后一行样式
                                .build();
                    } else {
                        excelWriter = EasyExcel.write(fileName).head(head())
                                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())//自动列宽
                                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 35, (short) 25))
                                .registerWriteHandler(horizontalCellStyleStrategy)//样式
                                .registerWriteHandler(new CustomCellWriteHandler(count + headRows))//自定义处理器去处理最后一行样式
                                .build();
                    }

                    // 这里注意 如果同一个sheet只要创建一次
                    writeSheet = EasyExcel.writerSheet("重复写入sheet模板").build();
                    isEnter = true;
                }
                // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                //                List<DemoData> data = data();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == 10) {
//                    List<List<String>> resultList = dynamicData();
                    List<List<String>> resultList = new ArrayList<>();
                    List<String> list1 = new ArrayList();
                    list1.add("合计111" + i);
                    list1.add("合计111" + i);
                    list1.add("最后一行111" + i);
                    list1.add("最后一行111" + i);
                    list1.add("最后一行111" + i);
                    resultList.add(list1);
                    List<String> lits = new ArrayList();
                    lits.add("合计_" + i);
                    lits.add("合计_" + i);
                    lits.add("最后一行_" + i);
                    lits.add("最后一行" + i);
                    lits.add("最后一行" + i);
                    resultList.add(lits);
                    excelWriter.write(resultList, writeSheet);
                    return;
                }

                excelWriter.write(dynamicData(), writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.close();
            }
        }
    }

    /**
     * 动态头、动态列写入，不创建对象的写，复杂头
     *
     * @return
     */
    @Test
    public void dynamicHeadExport() {
        String fileName = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        List<List<String>> lists = dynamicData();
        lists.add(Lists.newArrayList("合计", "合计", "合计", "ddd", "111"));
        System.out.println("lists size:" + lists.size());
        //头和内容的策略
//        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);
//        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, writeCellStyleList);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = buildHorizontalCellStyleStrategy();

        //合并单元格，最后一行，第1 - 3列合并
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(lists.size() + 1, lists.size() + 1, 0, 2);
        EasyExcel.write(fileName).head(head())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())//自动列宽
                .registerWriteHandler(horizontalCellStyleStrategy)//样式
                .registerWriteHandler(onceAbsoluteMergeStrategy)//合并单元格
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short) 35, (short) 25))//头行高：40 内容行高：25
                .registerWriteHandler(new CustomCellWriteHandler(lists.size() + 1))//自定义策略
                .sheet("动态头sheet")
                .doWrite(lists);
    }

    private HorizontalCellStyleStrategy buildHorizontalCellStyleStrategy() {
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
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    //样式


    /**
     * 合并单元格
     *
     * @return
     */
    @Test
    public void mergExport() {
        String fileName = filePath + "/easyExcel_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
        //第2列，每隔2行进行汇总
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 1);
        EasyExcel.write(fileName).head(head()).registerWriteHandler(loopMergeStrategy).sheet("合并sheet").doWrite(dynamicData());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(Lists.newArrayList("字符串"));
        list.add(Lists.newArrayList("数字"));
        list.add(Lists.newArrayList("日期"));
        list.add(Lists.newArrayList("复杂头", "复杂头1"));
        list.add(Lists.newArrayList("复杂头", "复杂头2"));
        return list;
    }

    private List<List<String>> dynamicData() {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < 10; i++) {
            List<String> head0 = new ArrayList<String>();
            head0.add("动态列1_" + i);
            head0.add("动态列2_" + i);
            head0.add("动态列3_" + i);
            head0.add("动态列4_复杂1" + i);
            head0.add("动态列4_复杂2" + i);
            list.add(head0);
        }
        return list;
    }


    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setName("列名" + i);
            data.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            data.setNumber(2673);
            list.add(data);
        }
        return list;
    }

    @Test
    public void ListsArrayList() {

        String[] titles = {"1", "2", "3", "4", "5"};
        ArrayList<String> strings = Lists.newArrayList(titles);
        System.out.println(strings);

        ArrayList<String> strings1 = Lists.newArrayList("复杂头", "复杂头1");
        ArrayList<String> strings2 = Lists.newArrayList("复杂头", "复杂头2");
        System.out.println(strings1);
        System.out.println(strings2);

    }
}
