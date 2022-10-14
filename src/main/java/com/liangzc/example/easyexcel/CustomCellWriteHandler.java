package com.liangzc.example.easyexcel;

import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.BooleanUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

@Slf4j
public class CustomCellWriteHandler implements CellWriteHandler {

    private Integer totalDataSize;

    public CustomCellWriteHandler(Integer totalDataSize) {
        this.totalDataSize = totalDataSize;
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        Cell cell = context.getCell();
        // 这里可以对cell进行任何操作
        log.info("第{}行，第{}列写入完成。", cell.getRowIndex(), cell.getColumnIndex());

        // 当前事件会在 数据设置到poi的cell里面才会回调
        // 判断不是头的情况 如果是fill 的情况 这里会==null 所以用not true
        if (BooleanUtils.isNotTrue(context.getHead())) {
            if(cell.getRowIndex() == totalDataSize){
                // 第一个单元格
                // 只要不是头 一定会有数据 当然fill的情况 可能要context.getCellDataList() ,这个需要看模板，因为一个单元格会有多个 WriteCellData
                WriteCellData<?> cellData = context.getFirstCellData();
                // 这里需要去cellData 获取样式
                // 很重要的一个原因是 WriteCellStyle 和 dataFormatData绑定的 简单的说 比如你加了 DateTimeFormat
                // ，已经将writeCellStyle里面的dataFormatData 改了 如果你自己new了一个WriteCellStyle，可能注解的样式就失效了
                // 然后 getOrCreateStyle 用于返回一个样式，如果为空，则创建一个后返回
                WriteCellStyle lastRowWriteCellStyle = cellData.getOrCreateStyle();
                lastRowWriteCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
                lastRowWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

                lastRowWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
                //背景设置为蓝色
                lastRowWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                lastRowWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
                lastRowWriteCellStyle.setBorderLeft(BorderStyle.THIN);
                lastRowWriteCellStyle.setBorderRight(BorderStyle.THIN);
                lastRowWriteCellStyle.setBorderTop(BorderStyle.THIN);
                lastRowWriteCellStyle.setBorderBottom(BorderStyle.THIN);
                WriteFont lastWriteFont = new WriteFont();
                lastWriteFont.setBold(true);
                lastWriteFont.setFontHeightInPoints((short) 13);
                lastWriteFont.setFontName("宋体");
                lastRowWriteCellStyle.setWriteFont(lastWriteFont);
                // 这样样式就设置好了 后面有个FillStyleCellWriteHandler 默认会将 WriteCellStyle 设置到 cell里面去 所以可以不用管了
            }
        }
    }
}
