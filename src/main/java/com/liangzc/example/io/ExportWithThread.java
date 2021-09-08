package com.liangzc.example.io;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExportWithThread {


    public static void main(String[] args) throws IOException {
//        ExportWithThread.exportWithThread();
        System.out.println((int) Math.ceil((double) 45620 / (double) 10000));

        List<String> lists = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        List<String> strings = lists.subList(0, 3);
        System.out.println(strings);
    }

    public static String exportWithThread() throws IOException {

        int initNum = 2;
        String name = "测试导出excel"+System.currentTimeMillis()+".xlsx";
        final FileOutputStream[] fileOutputStream = new FileOutputStream[1];

        List<String> lists = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        List<String> collect = lists.stream().limit(2).collect(Collectors.toList());
        List<String> collect1 = lists.stream().limit(4).collect(Collectors.toList());
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("测试导出shht页");

        int count = (int)Math.ceil((double) lists.size() /  (double) initNum);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fileOutputStream1;
                for (int i = 0; i < count; i++) {
                    for (int j = 0; j < lists.size(); j++) {
                        XSSFRow row = sheet.createRow(j + 1);
                        XSSFCell cell = row.createCell(0);
                        cell.setCellValue(lists.get(0));

                        XSSFCell cell1 = row.createCell(1);
                        cell1.setCellValue(lists.get(1));

                        XSSFCell cell2 = row.createCell(2);
                        cell2.setCellValue(lists.get(2));

                        XSSFCell cell3 = row.createCell(3);
                        cell3.setCellValue(lists.get(3));

                        XSSFCell cell4 = row.createCell(4);
                        cell4.setCellValue(lists.get(4));

                        XSSFCell cell5 = row.createCell(5);
                        cell5.setCellValue(lists.get(5));

                        XSSFCell cell6 = row.createCell(6);
                        cell6.setCellValue(lists.get(6));

                        XSSFCell cell7 = row.createCell(7);
                        cell7.setCellValue(lists.get(7));

                        XSSFCell cell8 = row.createCell(8);
                        cell8.setCellValue(lists.get(8));

                        XSSFCell cell9 = row.createCell(9);
                        cell9.setCellValue(lists.get(9));
                    }
                    try {
                        fileOutputStream1 = new FileOutputStream("D:/receive-file/DOWNLOAD_PATH/" + name);
                        workbook.write(fileOutputStream1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executorService.shutdown();

        return "success";
    }
}
