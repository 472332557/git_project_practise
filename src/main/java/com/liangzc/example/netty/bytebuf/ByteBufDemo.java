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

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("读数据索引：").append(byteBuf.readerIndex()).append(";");

        stringBuilder.append("写数据索引：").append(byteBuf.writerIndex()).append(";");

        stringBuilder.append("ByteBuf容量：").append(byteBuf.capacity()).append(";");

        ByteBufUtil.appendPrettyHexDump(stringBuilder, byteBuf);

        System.out.println(stringBuilder.toString());

    }
}
