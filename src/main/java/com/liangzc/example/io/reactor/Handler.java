package com.liangzc.example.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    private SocketChannel socketChannel;

    public Handler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        System.out.println("当前线程："+Thread.currentThread().getName());

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len=0,total=0;
        String msg = "";
        try {
            do {
                len = socketChannel.read(byteBuffer);
                if (len > 0) {
                    total += len;
                    msg += new String(byteBuffer.array());
                }
            } while (len > byteBuffer.capacity());

            //可以做后续的一些业务处理
            //msg=表示通信传输报文
            //耗时2s
            //登录： username:password
            //ServetRequets: 请求信息
            //数据库的判断
            //返回数据，通过channel写回到客户端
            System.out.println(socketChannel.getRemoteAddress()+"————>server receive msg："+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socketChannel != null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
