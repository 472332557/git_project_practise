package com.liangzc.example.netty.protocol;

import lombok.Data;

@Data
public class Header {

    private long sessionId;//8个字节长度的sessionId

    private byte reqType;//1个字节的请求类型

    private int contentLength;//4个字节的消息长度
}
