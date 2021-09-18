package com.liangzc.example.netty.protocol.server;

import com.liangzc.example.netty.protocol.codec.RecoedDecoder;
import com.liangzc.example.netty.protocol.codec.RecordEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtocolServer {

    public static void main(String[] args) {


        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup(4);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    //针对客户端连接来设置Pipeline
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                /*.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024,
                                        9,
                                        4,
                                        0,
                                        0))*/
                                .addLast(new RecordEncoder())
                                .addLast(new RecoedDecoder())
                                .addLast(new ServerHandler());
                    }
                });

        try {
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();//等待客户端连接同步阻塞
            log.info("ProtocolServer start success {8888}");
            channelFuture.channel().closeFuture().sync();//同步等待关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
