package com.liangzc.example.easyexcel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.SheetWriteHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * @Auther: liangzc
 * @Date: 2023/12/12 18:09
 * @Description:
 */
@Slf4j
public class CustomSheetWriteHandler implements SheetWriteHandler {
    @Override
    public void afterSheetCreate(SheetWriteHandlerContext context) {
        log.info("第{}个Sheet写入成功。", context.getWriteSheetHolder().getSheetNo());
        Integer lastRowIndex = context.getWriteSheetHolder().getLastRowIndex();
        log.info("最后一行{}", lastRowIndex);
        //需要对第4列数据进行下拉选项设置,由于第一行是头，所以从第二行开始
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 10, 3, 3);
        DataValidationHelper helper = context.getWriteSheetHolder().getSheet().getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[]{"测试1", "测试2"});
        DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);


        CellRangeAddressList cellRangeAddressList1 = new CellRangeAddressList(1, 10, 4, 4);
        DataValidationHelper helper1 = context.getWriteSheetHolder().getSheet().getDataValidationHelper();
        DataValidationConstraint constraint1 = helper1.createExplicitListConstraint(new String[]{"测试3", "测试4"});
        DataValidation dataValidation1 = helper.createValidation(constraint1, cellRangeAddressList1);

        context.getWriteSheetHolder().getSheet().addValidationData(dataValidation);
        context.getWriteSheetHolder().getSheet().addValidationData(dataValidation1);
    }
}
