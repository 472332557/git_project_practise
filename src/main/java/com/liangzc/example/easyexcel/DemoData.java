package com.liangzc.example.easyexcel;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    @ExcelProperty("列名标题")
    private String name;

    @ExcelProperty("日期")
    private String date;

    @ExcelProperty("数字值")
    private Integer number;

    @ExcelProperty("选项")
    private String choose;

    @ExcelProperty("选项2")
    private String choose1;
}
