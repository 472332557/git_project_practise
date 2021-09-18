package com.liangzc.example.netty.protocol;

import lombok.Data;

@Data
public class MessageRecord {

    private Header header;//消息头

    private Object content;//消息内容
}
