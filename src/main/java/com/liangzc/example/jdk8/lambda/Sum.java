package com.liangzc.example.jdk8.lambda;

public interface Sum {

    int add(Integer value);


    public static void main(String[] args) {

        int num1 = 6;
        Sum sum = value -> {
          return value + num1;
        };
        System.out.println(sum.add(1));
    }

    class SumTest{

        public int num1 = 6;
        public static int num2 = 8;

        private int getSum(){
         Sum sum = value -> {
             return num1 + num2 +value;
         };
            return sum.add(1);
        };

        public static void main(String[] args) {
            SumTest sumTest = new SumTest();
            System.out.println(sumTest.getSum());
        }
    }
}
