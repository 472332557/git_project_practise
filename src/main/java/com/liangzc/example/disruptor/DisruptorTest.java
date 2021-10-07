package com.liangzc.example.disruptor;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) {


        test2();
    }

    /**
     * 统一消费，定义多个消费者，每个消费者都消费一份生产者生产的数据
     */
    public static void test1(){
        Disruptor<MsgResult> disruptor = new Disruptor<MsgResult>(MsgResult::new,1024, Executors.defaultThreadFactory());

        //定义消费者
        MsgConsumer msgConsumer1 = new MsgConsumer();
//        MsgConsumer msgConsumer2 = new MsgConsumer();
//        MsgConsumer msgConsumer3 = new MsgConsumer();
        //绑定配置关系
//        disruptor.handleEventsWith(msgConsumer1,msgConsumer2,msgConsumer3);
        disruptor.handleEventsWith(msgConsumer1);
        disruptor.start();


        MsgProducer msgProducer = new MsgProducer(disruptor);
        MsgResult msgResult = new MsgResult();
        msgResult.setName("测试");
        msgResult.setValue("111");

        msgProducer.send(msgResult);
    }

    /**
     * 分组消费：每个生产者生产的数据只被消费一次
     */
    public static void test2(){
        Disruptor<MsgResult> disruptor = new Disruptor<MsgResult>(MsgResult::new,1024, Executors.defaultThreadFactory());
        disruptor.handleEventsWithWorkerPool(new MyWorkHandler("work1"),new MyWorkHandler("work2"));

        disruptor.start();

        List<MsgResult> msgResults = Arrays.asList(new MsgResult("测试1", "1"), new MsgResult("测试2", "2"));
        MsgProducer msgProducer = new MsgProducer(disruptor);
        msgProducer.send(msgResults);
    }



}
