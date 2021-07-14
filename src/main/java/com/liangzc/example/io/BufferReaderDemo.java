package com.liangzc.example.io;

import java.io.*;

public class BufferReaderDemo {

    public static void main(String[] args) {
        int i;
        String line;
        FileInputStream fis = null;
        if(args.length != 1){
            System.out.println(args.length);
            System.out.println("命令行参数有误");
            return;
        }
        try {

            fis = new FileInputStream(args[0]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\env\\uhome-config-export.properties")));
            do {
                line = reader.readLine();
                if(line != null){
                    System.out.println(line);
                    writer.write(line);
                }
            }while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    //关闭流
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
