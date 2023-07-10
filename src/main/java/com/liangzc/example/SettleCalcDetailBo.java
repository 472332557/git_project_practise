package com.liangzc.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettleCalcDetailBo {

    private Integer organId;

    private Long acctItemId;

    private Long srcAcctItemId;

    private Integer acctItemTypeId;

    private Integer feeItemTypeId;

    private String feeItemTypeName;

    private Integer billingCycle;

    private BigDecimal fee;

    private Integer paidFee;

    private BigDecimal lfree;

    private Integer paidLfree;

    private BigDecimal taxRate;

    private Integer taxFee;

    private BigDecimal lfreeTaxRate;

    private Integer lfreeTaxFee;

    private String billDateStart;

    private String billDateEnd;

    private Integer custId;

    private String custName;

    private Integer resInstId;

    private String resInstName;

    private Integer objId;

    private String objType;

    private String objCode;

    private String objName;

    private Long receivableDate;

    private BigDecimal billArea;

    private BigDecimal amount;

    private BigDecimal initVal;

    private BigDecimal endVal;

    private String rateStr;

    private Integer payLimitId;
}
