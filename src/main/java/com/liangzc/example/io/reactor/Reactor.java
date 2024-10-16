package com.liangzc.example.io.reactor;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor模型有三个重要组件：
 * · Reactor ：将I/O事件发派给对应的Handler。Reactor线程：负责多路分离套接字，有新连接到来触发connect 事件之后，交由Acceptor进行处理，有IO读写事件之后交给hanlder 处理。
 * · Acceptor ：处理客户端连接请求。Acceptor主要任务就是构建handler ，在获取到和client相关的SocketChannel之后 ，绑定到相应的hanlder上，
 * 对应的SocketChannel有读写事件之后，基于racotor 分发,hanlder就可以处理了（所有的IO事件都绑定到selector上，由Reactor分发）
 * · Handlers ：执行非阻塞读/写
 */
public class Reactor implements Runnable {


    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        //连接非阻塞，使用多路复用器模式，连接必须非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));//绑定端口
        //注册连接事件到多路复用器，由Acceptor去处理客户端连接请求
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(selector, serverSocketChannel));
    }


    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                //阻塞，等待一个事件（连接、IO事件）
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    dsipatcher(iterator.next());
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dsipatcher(SelectionKey selectionKey) {
        /**
         *  可能拿到的对象有两个
         *  Acceptor
         *  Handler
         */
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }


    public static void main(String[] args) throws IOException {

        Reactor reactor = new Reactor(8888);
        new Thread(reactor).start();

    }
}
