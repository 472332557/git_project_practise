package com.liangzc.example.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServerSocket {

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            serverSocket = new ServerSocket(8888);
            while (true){
                //表示阻塞等待一个客户端的连接，返回的socket表示连接的客户端信息
                Socket socket = serverSocket.accept();
                //使用线程池，IO变成了异步执行
                executorService.submit(new BIOServerSocketThreand(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
