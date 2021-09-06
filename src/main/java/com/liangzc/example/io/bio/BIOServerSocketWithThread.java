package com.liangzc.example.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServerSocketWithThread {

    public static void main(String[] args) {

        ServerSocket serverSocket =null;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("启动服务：监听端口：9999");
            while (true) {
                //表示阻塞等待一个客户端连接，返回的socket表示连接的客户端信息
                Socket socket = serverSocket.accept();
                System.out.println("客户端：" + socket.getPort());
                //IO变成了异步，就不会被阻塞了
                executorService.execute(new SocketThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
