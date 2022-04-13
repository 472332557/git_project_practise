package com.liangzc.example.disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

public class MyWorkHandler implements WorkHandler<MsgResult> {

    private String name;

    public MyWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(MsgResult msgResult) throws Exception {
        TimeUnit.SECONDS.sleep(60);
        System.out.println(1 / 0);
        System.out.println(this.name +"------接受到信息:"+msgResult);
    }
}
