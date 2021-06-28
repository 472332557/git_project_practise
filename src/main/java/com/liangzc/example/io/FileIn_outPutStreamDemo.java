package com.liangzc.example.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileIn_outPutStreamDemo {

    public static void main(String[] args) {
        int i;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        if(args.length != 2){
            System.out.println(args.length);
            System.out.println("命令行参数有误");
            return;
        }
        try {

            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);
            do {
                i = fis.read();
                if(i != -1){
                    System.out.println((char) i);
                    fos.write(i);
                }
            }while (i != -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null){
                    fis.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
