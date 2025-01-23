package com.liangzc.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyBasicServerExample {


    public static void main(String[] args) {


        //主线程，主要接收客户端连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程组，主要处理连接事件后的IO操作
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        //构建Netty Server的API
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                //指定epoll模型
                .channel(NioServerSocketChannel.class)
                //具体的工作处理类，负责处理相关SocketChannel的IO就绪事件，可以类比为reator模型中的handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //心跳的hander
                        //编解码
                        //协议处理
                        //消息处理
                        socketChannel.pipeline().
                                addLast(new NormalChannelHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();//同步阻塞等到客户端连接
            System.out.println("Netty Server Started Success:listener port:8888");
            channelFuture.channel().closeFuture().sync();//同步等到服务端监听端口关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
