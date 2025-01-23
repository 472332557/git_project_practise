package com.liangzc.example.io.bio;

import java.io.*;
import java.net.Socket;

public class SocketThread implements Runnable {

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        //表示获得客户端发送的消息，InputStream是阻塞的
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = bufferedReader.readLine();
            System.out.println("接收到了客户端发送的一条信息:" + data);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("receive a message:" + data);
            bufferedWriter.newLine();//换行，表示写完了，否则一直处于阻塞
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
