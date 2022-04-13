package com.liangzc.example.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class CustomerException implements ExceptionHandler<MsgResult> {

    @Override
    public void handleEventException(Throwable ex, long sequence, MsgResult event) {

        ex.printStackTrace();

        System.out.println("Exception processing: " + sequence + " " + event+ ex);
    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
