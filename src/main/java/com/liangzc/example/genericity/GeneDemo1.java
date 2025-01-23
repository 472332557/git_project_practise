package com.liangzc.example.genericity;

/**
 * 两个类型形参的泛型
 *
 * @param <T> 形参T
 * @param <V> 形参V
 */
public class GeneDemo1<T, V> {

    T objT;
    V objV;

    public GeneDemo1(T objT, V objV) {
        this.objT = objT;
        this.objV = objV;
    }


    public T getT() {

        return objT;
    }

    public V getV() {
        return objV;
    }

    public void print() {

        System.out.println("T is:" + objT + ",and V is:" + objV);
    }

    public void type() {

        System.out.println("type of T is :" + objT.getClass().getName() + "\n" + "type of V is :" + objV.getClass().getName());
    }

    public static void main(String[] args) {
        GeneDemo1<Integer, String> geneDemo1 = new GeneDemo1<>(1, "first");
        System.out.println(geneDemo1.getT());
        System.out.println(geneDemo1.getV());
        geneDemo1.print();
        geneDemo1.type();
    }
}
