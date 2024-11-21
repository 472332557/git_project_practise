package com.liangzc.example.json_test;

import lombok.Data;

/**
 * @Auther: liangzc
 * @Date: 2024/10/16 15:52
 * @Description:
 */
@Data
public class ChargeObjectCustomer {

    private Long chargeObjectId;

    private String chargeObjectType;

    private Long customerId;

    private String customerType;
}
