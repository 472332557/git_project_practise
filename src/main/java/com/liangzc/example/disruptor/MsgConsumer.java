package com.liangzc.example.disruptor;

import com.lmax.disruptor.EventHandler;

public class MsgConsumer implements EventHandler<MsgResult> {

    @Override
    public void onEvent(MsgResult msgResult, long l, boolean b) throws Exception {
        System.out.println("【"+Thread.currentThread().getName()+"】"+"------接受到信息:"+msgResult);
    }
}
