package com.liangzc.example.netty.protocol.server;

import com.liangzc.example.netty.protocol.MessageRecord;
import com.liangzc.example.netty.protocol.ReqTypeE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ServerHandler， 应该是SocketChannel触发的。
        MessageRecord messageRecord = (MessageRecord) msg;
        log.info("服务端收到了客户端请求：{}",messageRecord);
        //反写回客户端
        messageRecord.setContent("Server Response Message");
        messageRecord.getHeader().setReqType(ReqTypeE.RESPONSE.getType());
        ctx.writeAndFlush(messageRecord);
//        super.channelRead(ctx, msg);
    }
}
