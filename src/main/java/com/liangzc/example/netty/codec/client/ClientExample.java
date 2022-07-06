package com.liangzc.example.netty.codec.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;

import java.net.InetSocketAddress;
import java.util.UUID;

public class ClientExample {
    public static void main(String[] args) {
        EventLoopGroup workEvent = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workEvent)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                //服务端使用LengthFieldBasedFrameDecoder解码器，客户端需要指定报文length长度。表示传输消息的时候，在消息报文中增加4个字节的length。->发送的ByteBuf
                                .addLast(new LengthFieldPrepender(4,0,false))
                                .addLast(new ChannelInboundHandlerAdapter(){

                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println("客户端连接建立成功");
                                        StringBuilder builder = new StringBuilder();
                                        for (int i = 0; i < 10; i++) {
                                            builder.append(UUID.randomUUID()).append(i).append("&");
                                        }
                                        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
                                        byteBuf.writeBytes(builder.toString().getBytes());
                                        ctx.writeAndFlush(byteBuf);
//                                        super.channelActive(ctx);
                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf byteBuf = (ByteBuf) msg;
                                        byte[] bytes = new byte[byteBuf.readableBytes()];
                                        byteBuf.readBytes(bytes);
                                        String readMsg = new String(bytes, "utf-8");
                                        System.out.println("客户端接收到服务端返回信息："+readMsg);
                                        super.channelActive(ctx);
                                    }
                                });
                    }
                });

        try {
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8888)).sync();
            channelFuture.channel().closeFuture().sync();//同步等待客户端关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
