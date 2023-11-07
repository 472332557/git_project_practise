package com.liangzc.example.jdk8.lambda;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 向振华
 * @date 2021/12/23 15:09
 */
@Data
public class ReceivableOverviewNormalPageVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long chargeObjectId;


    private String chargeObjectType;


    private String chargeObjectCode;


    private String chargeObjectName;


    private String buildingName;


    private String parkName;

    private String spaceName;

    private String roomNo;

    private String publicArea;


    private String parkLotIds;


    private String parkLot;


    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;


    private String customerType;


    private String customerName;


    @JsonSerialize(using = ToStringSerializer.class)
    private Long masterOwnerId;


    private String ownerName;


    private Boolean ownerStatus;


    private BigDecimal totalPayableAmount;


    private BigDecimal totalPaidAmount;


    private BigDecimal totalPendingAmount;


    private BigDecimal penalPendingAmount;

    private BigDecimal advanceBalanceAmount;
}