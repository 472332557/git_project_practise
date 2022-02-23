package com.liangzc.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadTest {
    public static void main(String[] args) throws IOException {

        String basePath = "D:/twscerts/C00000000X77";

        File file = new File(basePath);

        System.out.println("是否是文件："+file.isFile());

        System.out.println("是否存在："+file.exists());

        System.out.println("是否是一个文件夹："+file.isDirectory());

        String pfxPath = "";
        String crtPath = "";
        String iniPath = "";

        for (File listFile : file.listFiles()) {
            System.out.println(listFile.isFile());
            System.out.println("存在否："+listFile.exists());
            System.out.println("文件路径："+listFile.getPath());
            System.out.println("文件名称："+listFile.getName());
            if (listFile.getName().endsWith(".pfx")){
                pfxPath = listFile.getPath();
            }
            if (listFile.getName().endsWith(".crt")){
                crtPath = listFile.getPath();
            }
            if(listFile.getName().endsWith(".ini")){
                iniPath = listFile.getPath();
            }
        }

        System.out.println("pfxPath:"+pfxPath);
        System.out.println("crtPath:"+crtPath);
        System.out.println("iniPath:" + iniPath);

        InputStream inputStream = new FileInputStream(iniPath);
        int read =0;
        StringBuilder sb = new StringBuilder();
/*        while ((read = inputStream.read()) != -1){
            sb.append((char) read);
        }*/
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1){
            sb.append(new String(bytes, 0, read));
        }

        System.out.println(sb);



    }
}
