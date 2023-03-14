package com.liangzc.example.redis.hashedwheeltime;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimeDemo {

    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

    @Test
    public void test() throws IOException {
        System.out.println("currentTime:"+new Date());

        //一个任务延迟5秒后执行
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("延迟5秒后执行beginExecute:"+new Date());
                timeout.timer().newTimeout(timeout1 -> {
                    System.out.println("再延迟5秒后执行againExecute:"+new Date());
                },5000,TimeUnit.MILLISECONDS);
            }
        }, 5000, TimeUnit.MILLISECONDS);

        //等待键盘读取一个字符，防止方法结束，演示不了时间轮的延迟执行
        System.in.read();

    }
}
