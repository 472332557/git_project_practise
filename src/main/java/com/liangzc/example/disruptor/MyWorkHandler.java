package com.liangzc.example.disruptor;

import com.lmax.disruptor.WorkHandler;

public class MyWorkHandler implements WorkHandler<MsgResult> {

    private String name;

    public MyWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(MsgResult msgResult) throws Exception {
        System.out.println(this.name +"------接受到信息:"+msgResult);
    }
}
