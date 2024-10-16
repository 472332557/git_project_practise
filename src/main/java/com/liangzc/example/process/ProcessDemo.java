package com.liangzc.example.process;

import java.io.IOException;

public class ProcessDemo {

    public static void main(String[] args) {


        ProcessBuilder processBuilder = new ProcessBuilder("notepad", "test1");
        try {
            Process process = processBuilder.start();
//            process.waitFor();
            long l = System.currentTimeMillis();
            Thread.sleep(2000);
            System.out.println(System.currentTimeMillis() - l);
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
