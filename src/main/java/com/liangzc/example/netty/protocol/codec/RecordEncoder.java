package com.liangzc.example.netty.protocol.codec;

import com.liangzc.example.netty.protocol.Header;
import com.liangzc.example.netty.protocol.MessageRecord;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

@Slf4j
public class RecordEncoder extends MessageToByteEncoder<MessageRecord> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageRecord messageRecord, ByteBuf out) throws Exception {
        log.info("==============================开始对消息进行编码==================================");
        Header header = messageRecord.getHeader();//得到消息协议的header部分
        out.writeLong(header.getSessionId());//写8个字节的sessionid
        out.writeByte(header.getReqType());//1个字节的消息类型
        Object content = messageRecord.getContent();
        if (content != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(content);
            byte[] bytes = bos.toByteArray();
            out.writeInt(bytes.length);//4个字节的写消息长度
            out.writeBytes(bytes);//消息内容
        } else {
            out.writeInt(0);
        }

    }
}
