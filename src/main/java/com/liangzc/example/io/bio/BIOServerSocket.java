package com.liangzc.example.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServerSocket {

    public static void main(String[] args) {


        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("启动服务：监听端口：9999");
            while (true) {
                //表示阻塞等待一个客户端连接，返回的socket表示连接的客户端信息
                Socket socket = serverSocket.accept();
                System.out.println("客户端：" + socket.getPort());
                //表示获得客户端发送的消息，InputStream是阻塞的
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String data = bufferedReader.readLine();
                System.out.println("接收到了客户端发送的一条信息:" + data);

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write("receive a message:" + data);
                bufferedWriter.newLine();//换行，表示写完了，否则一直处于阻塞
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
