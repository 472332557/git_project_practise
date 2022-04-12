package com.liangzc.example.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class CustomerException implements ExceptionHandler {
    @Override
    public void handleEventException(Throwable throwable, long l, Object o) {

    }

    @Override
    public void handleOnStartException(Throwable throwable) {

    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {

    }
}
