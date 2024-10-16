package com.liangzc.example.disruptor;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

public class MsgConsumer implements EventHandler<MsgResult> {

    @Override
    public void onEvent(MsgResult msgResult, long l, boolean b) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(1 / 0);
        System.out.println("【" + Thread.currentThread().getName() + "】" + "------接受到信息:" + msgResult);
    }
}
