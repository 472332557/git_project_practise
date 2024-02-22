package com.liangzc.example.em;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: liangzc
 * @Date: 2024/2/22 09:29
 * @Description:
 */
@Getter
public enum PayChannelEnum {


    /**
     * PING++
     */
    PAY_PING_PLUS_PLUS("PAY_PING_PLUS_PLUS",1,"PING++","线上支付"),

    /**
     * 通联支付（账户类型确定子通道）
     */
    PAY_COMM_UNION("PAY_COMM_UNION",2,"通联支付","线上支付"),

    /**
     * 建行支付
     */
    CCB_PAY("CCB_PAY",3,"建行支付","建行支付"),
//
    /**
     * 深圳通联
     */
    COL_COMMON_UNION("COL_COMMON_UNION",4,"深圳通联","托收"),

    /**
     *  广州银联
     */
    COL_GUANGZHOU_UNION("COL_GUANGZHOU_UNION",5,"广州银联","托收"),

    /**
     * 金融联
     */
    COL_FINANCE_UNION("COL_FINANCE_UNION",6,"金融联","托收"),

    /**
     * 线下银行
     */
    COL_OFFLINE_BANK("COL_OFFLINE_BANK",7,"线下银行","托收"),


    /**
     * 其他
     */
    PAY_OTHER("PAY_OTHER",8,"其他","其他"),

    /**
     * 随行付
     */
    PAY_SUIXINGFU("PAY_SUIXINGFU",9,"随行付","线上支付"),

    /**
     * 快钱
     */
    PAY_KUAIQIAN("PAY_KUAIQIAN",10,"快钱支付","线上支付");


    private String code;
    private Integer value;
    private String text;
    private String displayText;

    PayChannelEnum(String code, Integer value, String text,String displayText) {
        this.code = code;
        this.value = value;
        this.text = text;
        this.displayText = displayText;
    }
    public static PayChannelEnum getByCode( String itemCode) {
        if (StringUtils.isNotBlank(itemCode)) {
            for (PayChannelEnum type : PayChannelEnum.values()) {
                if (type.code.equals(itemCode)) {
                    return type;
                }
            }
        }
        return null;
    }

    public static String getTextByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (PayChannelEnum e : PayChannelEnum.values()) {
                if (e.code.equals(code)) {
                    return e.text;
                }
            }
        }
        return "";
    }
}
