package com.liangzc.example.genericity;

/**
 * 有时候，我们需要将泛型的类型指定一个范围，此时可以采用有界类型的方式，给该类型一个上界
 * 有界类型，形参使用extends指定类型的上界，类型实参必须是指定的超类或者该超类的子类对象
 * @param <T>
 */
public class GeneDemo2<T extends Number> {

    T objT;

    public GeneDemo2(T objT) {
        this.objT = objT;
    }

    public double method1(){
        return 1 / objT.doubleValue();
    }
    public double method2(){
        return objT.doubleValue() - objT.intValue();
    }

    public boolean mathEquals(GeneDemo2<T> obj){
        if(Math.abs(objT.doubleValue()) == Math.abs(obj.objT.doubleValue())){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        /*GeneDemo2<Double> geneDemo2 = new GeneDemo2<>(5.5);
        System.out.println(geneDemo2.method1());
        System.out.println(geneDemo2.method2());*/

        GeneDemo2<Integer> geneDemo2 = new GeneDemo2<>(6);
        GeneDemo2<Double> geneDemo21 = new GeneDemo2<>(-6.0);
//        geneDemo2.mathEquals(geneDemo21);//会报错，编译不通过，需要的类型为Integer类型
    }
}
