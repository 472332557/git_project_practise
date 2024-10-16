package com.liangzc.example.io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {

    public static void main(String[] args) {
        int i;
        FileInputStream fis = null;
        if (args.length != 1) {
            System.out.println(args.length);
            System.out.println("命令行参数有误");
            return;
        }
        try {

            fis = new FileInputStream(args[0]);
            do {
                i = fis.read();
                if (i != -1) {
                    System.out.println((char) i);
                }
            } while (i != -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
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
