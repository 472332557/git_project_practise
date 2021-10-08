package com.liangzc.example.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class MsgProducer {

    private Disruptor disruptor;

    public MsgProducer(Disruptor disruptor) {
        this.disruptor = disruptor;
    }

    public void send(MsgResult msgResult){
        RingBuffer<MsgResult> ringBuffer = this.disruptor.getRingBuffer();
        //获取下一个放置数据的位置
        long next = ringBuffer.next();

        MsgResult msgResult1 = null;
        try {
            msgResult1 = ringBuffer.get(next);
            BeanUtils.copyProperties(msgResult, msgResult1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //发布事件
            ringBuffer.publish(next);
        }
    }

    public void send(List<MsgResult> msgResults){
        msgResults.stream().forEach(e -> this.send(e));
    }
}
