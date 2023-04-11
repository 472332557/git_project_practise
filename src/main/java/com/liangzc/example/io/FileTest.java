package com.liangzc.example.io;

import java.io.*;
import java.util.Properties;

public class FileTest {

    public static void main(String[] args) {

        Properties properties = new Properties();

        File file = new File("D:\\env\\uhome-config.properties");
        System.out.println(file.getName());
        System.out.println(file.isDirectory());
        System.out.println(file.getPath());

        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            properties.load(inputStream);
            System.out.println(properties.getProperty("DOWNLOAD_TMP_PATH"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
