package com.liangzc.example.thread;

import java.util.concurrent.Executors;

public class Tets {

    public static void main(String[] args) {

        Executors.newFixedThreadPool(1).execute(new ThreadCountTest());
    }
}
