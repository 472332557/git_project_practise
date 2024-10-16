package com.liangzc.example.io.reactor;

import lombok.SneakyThrows;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @SneakyThrows
    @Override
    public void run() {
        //得到一个客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println(socketChannel.getRemoteAddress() + ":收到一个客户端连接");
        //IO非阻塞
        socketChannel.configureBlocking(false);
        //注册读事件到多路复用器
        socketChannel.register(selector, SelectionKey.OP_READ, new Handler(socketChannel));
    }
}
