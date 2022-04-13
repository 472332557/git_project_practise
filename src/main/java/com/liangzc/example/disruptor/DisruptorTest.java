package com.liangzc.example.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class DisruptorTest {

    public static void main(String[] args) {


//        test2();
        test1();
    }

    /**
     * 统一消费，定义多个消费者，每个消费者都消费一份生产者生产的数据
     */
    public static void test1(){
        Disruptor<MsgResult> disruptor = new Disruptor<MsgResult>(MsgResult::new,4, Executors.defaultThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());

        //设置自定义异常处理器，不设置会使用默认的异常处理器：FatalExceptionHandler，该处理器会直接抛出异常，导致程序终止，后续任务也不会执行
        disruptor.setDefaultExceptionHandler(new CustomerException());


        //定义消费者
        MsgConsumer msgConsumer1 = new MsgConsumer();
//        MsgConsumer msgConsumer2 = new MsgConsumer();
//        MsgConsumer msgConsumer3 = new MsgConsumer();
        //绑定配置关系
//        disruptor.handleEventsWith(msgConsumer1,msgConsumer2,msgConsumer3);
        disruptor.handleEventsWith(msgConsumer1);
        disruptor.start();


        MsgProducer msgProducer = new MsgProducer(disruptor);
//        List<MsgResult> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            MsgResult msgResult = new MsgResult();
            msgResult.setName("测试");
            msgResult.setValue(i+"");
//            list.add(msgResult);
            msgProducer.send(msgResult);
        }
//        msgProducer.send(list);
    }

    /**
     * 分组消费：每个生产者生产的数据只被消费一次
     */
    public static void test2(){
        Disruptor<MsgResult> disruptor = new Disruptor<MsgResult>(MsgResult::new,4, Executors.defaultThreadFactory());

        disruptor.setDefaultExceptionHandler(new CustomerException());

        disruptor.handleEventsWithWorkerPool(new MyWorkHandler("work1"),new MyWorkHandler("work2"));

        disruptor.start();

//        List<MsgResult> msgResults = Arrays.asList(new MsgResult("测试1", "1"), new MsgResult("测试2", "2"));
        MsgProducer msgProducer = new MsgProducer(disruptor);
//        msgProducer.send(msgResults);

        for (int i = 0; i < 100000; i++) {
            MsgResult msgResult = new MsgResult();
            msgResult.setName("测试");
            msgResult.setValue(i+"");
//            list.add(msgResult);
            msgProducer.send(msgResult);
        }
    }



}
