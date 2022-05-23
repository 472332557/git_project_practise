package com.liangzc.example.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.BeanUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
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
        long ringBufferSise = ringBuffer.remainingCapacity();
        System.out.println("ringBuffer 大小："+ringBufferSise);

        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memory.getHeapMemoryUsage();
        System.out.println("初始化大小："+heapMemory.getInit()/(1024*1024));
        System.out.println("最大："+heapMemory.getMax()/(1024*1024));
        System.out.println("已使用："+heapMemory.getUsed()/(1024*1024));

        MsgResult msgResult1 = null;
        try {
//            msgResult1 = ringBuffer.get(next);
//            System.out.println("ringBuffer.get(next)获得的值："+msgResult1);
//            BeanUtils.copyProperties(msgResult, msgResult1);
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
