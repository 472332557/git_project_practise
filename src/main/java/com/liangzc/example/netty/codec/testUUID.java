package com.liangzc.example.netty.codec;

import java.util.UUID;

public class testUUID {

    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().length());
    }
}
