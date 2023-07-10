package com.liangzc.example;

import lombok.Data;

import java.util.List;

@Data
public class ContractDetaiDto {

    private String contractId;
    private String contractNo;
    private String totalRentArea;
    private String secondId;
    private String secondName;
    private String secondType;
    private String secondCredType;
    private String secondCredNo;
    private String contractResource;
    private String startDate;
    private String endDate;
    private String depositMoney;
    private String preTaxAmount;
    private String afterTaxAmount;
    private String taxAmount;
    private String firstLease;
    private String averagePrice;
    private String processTimes;
    private String contractDuration;
    private List<ResInstObj> resList;
}

