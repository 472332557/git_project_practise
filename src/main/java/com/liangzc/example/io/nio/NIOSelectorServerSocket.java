package com.liangzc.example.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOSelectorServerSocket implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public NIOSelectorServerSocket(int port) throws IOException {
        selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                selector.select();//阻塞，等待就绪事件（连接 | IO 事件（read、write））
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//事件列表
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //说明有连接进来
                    SelectionKey selectionKey = iterator.next();
                    dispatcher(selectionKey);
                    iterator.remove();//移除当前就绪的事件
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 由于可以注册多个连接，所以会有连接、读、写事件，需要做一个判断
     * 需要明白的是：当第一次建立连接，注册为一个读事件后，下次可能会是一个读事件，所以此时可以去做读的操作
     * 由于可以进行多个注册，可能下次会注册一个连接事件（意味着又有新的客户端进来了），所以进行判断处理
     *
     * @param selectionKey
     * @throws IOException
     */
    private void dispatcher(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {//连接事件
            register(selectionKey);
        } else if (selectionKey.isReadable()) {//读事件
            read(selectionKey);
        } else if (selectionKey.isWritable()) {//写事件
//            write(selectionKey);
        }
    }

    private void write(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("dddddddd".getBytes());
        socketChannel.write(byteBuffer);
        System.out.println("server send a message：" + new String(byteBuffer.array()));
    }

    private void read(SelectionKey selectionKey) throws IOException {
        //此时是读事件，所以是SocketChannel，客户端连接进行读取
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //申请一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读取的数据放到缓冲区
        socketChannel.read(byteBuffer);

        System.out.println("server receive a message：" + new String(byteBuffer.array()));

        byteBuffer.flip();
        socketChannel.write(byteBuffer);

//        socketChannel.register(selector, SelectionKey.OP_WRITE);//注册为写事件
    }

    private void register(SelectionKey selectionKey) throws IOException {
        /**
         * 如果当前有连接事件进来的话，ServerSocketChannel一定是有的
         * 从而获得客户端连接SocketChannel
         */
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        //此时一定可以获取到客户端连接
        SocketChannel socketChannel = channel.accept();
        //建立了连接，不一定会进行IO操作，所以要设置为IO非阻塞
        socketChannel.configureBlocking(false);
        //客户端建立连接，此时server端就要读取客户端数据了，但是客户端不一定有写，所以再丢给多路复用器selector去监听
        //一旦连接就绪后（有写以后），再给我返回，此时就是read事件了
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        NIOSelectorServerSocket nioSelectorServerSocket = new NIOSelectorServerSocket(8888);
        new Thread(nioSelectorServerSocket).start();
    }
}
