package com.liangzc.example.netty.protocol.client;

import com.liangzc.example.netty.protocol.MessageRecord;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRecord record = (MessageRecord) msg;
        log.info("client Receive Message:" + record);
        super.channelRead(ctx, msg);
    }
}
