package com.liangzc.example.genericity;

/**
 * 使用通配符的实参
 * <p>
 * 比如要比较两个GeneDemo3对象的包括的数的绝对值是否相等，调用mathEquals（）方法
 * 一个对象实参类型是Integer 的 6，另一个对象实参类型是Double的 -6.0，此时调用mathEquals方法，发现编译不通过，
 * 它只适用于与调用对象类型相同的GeneDemo3对象，如果调用对象是GeneDemo3<Integer>类型，则形参obj也必须是GeneDemo3<Integer>类型
 * 如果形参类型是Double类型，则编译不通过，需要的形参类型为Integer类型，那如何比较Integer 类型和 Double类型的对象呢？
 * <p>
 * 使用通配符实参，使用 ？ 指定，表示未知类型。
 *
 * @param <T>
 */
public class GeneDemo3<T extends Number> {

    T num;

    public GeneDemo3(T num) {
        this.num = num;
    }

    public boolean mathEquals(GeneDemo3<?> obj) {
        if (Math.abs(num.doubleValue()) == Math.abs(obj.num.doubleValue())) {
            System.out.println("ok------------------------");
            return true;
        }
        System.out.println("no----------------------------");
        return false;
    }


    public static void main(String[] args) {
        GeneDemo3<Integer> geneDemo3 = new GeneDemo3<>(6);
        GeneDemo3<Double> geneDemo31 = new GeneDemo3<>(-6.0);
        geneDemo3.mathEquals(geneDemo31);
    }
}
