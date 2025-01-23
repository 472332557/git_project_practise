package com.liangzc.example.design_pattern.head_first.singleton_model;

/**
 * 懒汉式单例模式：延迟实例化对象的时机，等需要时再实例化，避免了内存浪费
 * 缺点：多线程环境下，单例会失效
 */
public class LazySingletonModel {

    //volatile禁止指令重排序
    private static volatile LazySingletonModel singletonModel;

    private LazySingletonModel() {

    }

    /**
     * 方法加锁会影响性能，试想，每次第一次获取对象时，需要被加锁，之后其他线程虽然无法创建新的实例，但还是要被阻塞，所以移到对象创建那里加锁
     *
     * @return
     */
    public /*synchronized*/ static LazySingletonModel getInstance() {

        if (singletonModel == null) {
            synchronized (LazySingletonModel.class) {
                if (singletonModel == null) {
                    return new LazySingletonModel();//这里再判断一次，线程还是可能会创建多个实例出来
                }

            }
        }
        return singletonModel;
    }
}
