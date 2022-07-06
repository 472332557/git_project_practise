package com.liangzc.example.netty.codec.server;

import com.liangzc.example.netty.NormalChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.charset.Charset;

public class ServerExample {

    public static void main(String[] args) {

        //构建boss工作组,负责接收I/O连接事件，之后将事件注册至workEvent工作组当中的Selector（多路复用器）上
        EventLoopGroup bossEvent = new NioEventLoopGroup();
        //构建work工作组
        EventLoopGroup workEvent = new NioEventLoopGroup(4);
        //构建netty server
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEvent,workEvent)
                //指定channel模型
                .channel(NioServerSocketChannel.class)
                //构建具体的工作处理类，负责处理相关SocketChannel的IO就绪事件，可以类比为reator模型中的handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ByteBuf byteBuf = Unpooled.copiedBuffer("&", Charset.forName("utf-8"));
                socketChannel.pipeline()
                        //固定长度的解码：FixedLengthFrameDecoder
//                        .addLast(new FixedLengthFrameDecoder(36))
                        //特殊分隔符的解码：DelimiterBasedFrameDecoder
//                        .addLast(new DelimiterBasedFrameDecoder(1024 * 1024, byteBuf))
                        /**
                         *  长度域解码器：LengthFieldBasedFrameDecoder
                         *  maxFrameLength：报文最大长度
                         *  lengthFieldOffset：Length字段起始偏移量
                         *  lengthFieldLength：Length字段长度所占的字节数
                         *  lengthAdjustment：消息长度的修正值，
                         *  initialBytesToStrip：解码后需要跳过的初始字节数，很多时候，调用者只关心包的内容，包的头部完全可以丢弃掉，
                         *                      initialBytesToStrip就是用来告诉Netty，识别出整个数据包之后，我只要从initialBytesToStrip起的数据
                         */
                        .addLast(new LengthFieldBasedFrameDecoder(1024*1024,0,4,0,0))
                        .addLast(new ServerNormalChannelHandler());
            }
        });

        try {
            //同步等待客户端连接
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            System.out.println("Netty Server Started Success:listener port:8888");
            //同步等到服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossEvent.shutdownGracefully();
            workEvent.shutdownGracefully();
        }

    }
}
