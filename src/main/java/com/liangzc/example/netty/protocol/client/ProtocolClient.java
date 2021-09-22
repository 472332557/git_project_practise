package com.liangzc.example.netty.protocol.client;

import com.liangzc.example.netty.protocol.Header;
import com.liangzc.example.netty.protocol.MessageRecord;
import com.liangzc.example.netty.protocol.ReqTypeE;
import com.liangzc.example.netty.protocol.codec.RecoedDecoder;
import com.liangzc.example.netty.protocol.codec.RecordEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.net.InetSocketAddress;

public class ProtocolClient {

    public static void main(String[] args) {

        EventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(1024 * 1024,
                                        9,
                                        4,
                                        0,
                                        0))
                                .addLast(new RecordEncoder())
                                .addLast(new RecoedDecoder())
                                .addLast(new ClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8888)).sync();
            Channel channel = channelFuture.channel();
            for (int i = 0; i < 100; i++) {
                MessageRecord messageRecord = new MessageRecord();
                Header header = new Header();
                header.setSessionId(111121321);
                header.setReqType(ReqTypeE.REQUEST.getType());
                messageRecord.setHeader(header);
                String content = "这是客户端发送请求"+i;
                messageRecord.setContent(content);
                channel.writeAndFlush(messageRecord);
            }
            channelFuture.channel().closeFuture().sync();//同步等待客户端关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
        }
    }
}
