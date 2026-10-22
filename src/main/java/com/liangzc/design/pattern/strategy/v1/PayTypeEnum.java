package com.liangzc.design.pattern.strategy.v1;

public enum PayTypeEnum {

    ALI_PAY(1, "支付宝"),
    WECHAT_PAY(2, "微信支付"),
    BANK_PAY(3, "银行支付");

    PayTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
