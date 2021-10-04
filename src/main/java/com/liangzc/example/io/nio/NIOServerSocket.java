package com.liangzc.example.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServerSocket {

    public static void main(String[] args) {

        /**
         * NIO三个核心
         * channel
         * buffer
         * selector
         * IO是同步阻塞的，线程不断的轮训去获取客户端信息，这样很浪费cpu资源个无效的轮训
         */
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//设置连接非阻塞
            serverSocketChannel.socket().bind(new InetSocketAddress(8888));
            while (true){
                //获得客户端连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                    Thread.sleep(1000);

                    byteBuffer.flip();//反转
                    socketChannel.write(byteBuffer);
                }else {
                    Thread.sleep(1000);
                    System.out.println("连接未就绪！");
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
