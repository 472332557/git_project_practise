package com.liangzc.example.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

import java.nio.charset.StandardCharsets;

public class ByteBufDemo {

    public static void main(String[] args) {


        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(byteBuf);

        System.out.println("=====================================before======================================");
        logByteBuf(byteBuf);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            stringBuilder.append("-" + i);
        }
        byteBuf.writeBytes(stringBuilder.toString().getBytes());
        System.out.println("=====================================after======================================");
        logByteBuf(byteBuf);
    }

    private static void logByteBuf(ByteBuf byteBuf) {

        System.out.println("读数据之前："+byteBuf.readerIndex());

        System.out.println("写数据之后："+byteBuf.writerIndex());

        System.out.println("ByteBuf容量："+byteBuf.capacity());

    }
}
