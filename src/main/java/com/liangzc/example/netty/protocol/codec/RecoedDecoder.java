package com.liangzc.example.netty.protocol.codec;

import com.liangzc.example.netty.protocol.Header;
import com.liangzc.example.netty.protocol.MessageRecord;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 反序列化消息内容
 */
@Slf4j
public class RecoedDecoder extends ByteToMessageDecoder {

    /**
     * sessionId | reqType | Content-length | Content
     *
     * @param channelHandlerContext
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws IOException, ClassNotFoundException {
        log.info("=================================开始对消息进行解码===================================");
        MessageRecord record = new MessageRecord();
        Header header = new Header();
        header.setSessionId(in.readLong());
        header.setReqType(in.readByte());
        header.setContentLength(in.readInt());
        record.setHeader(header);
        if (header.getContentLength() > 0) {
            byte[] contents = new byte[header.getContentLength()];
            in.readBytes(contents);//读取消息到contents字节数组中
            /**
             * Java原生的对象流
             */
            ByteArrayInputStream bis = new ByteArrayInputStream(contents);
            ObjectInputStream ois = new ObjectInputStream(bis);
            record.setContent(ois.readObject());
            log.info("反序列化出来的结果：{}", record);
            out.add(record);
        } else {
            log.info("消息为空！");
        }
    }
}
