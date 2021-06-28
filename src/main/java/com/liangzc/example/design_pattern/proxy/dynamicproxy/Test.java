package com.liangzc.example.design_pattern.proxy.dynamicproxy;

public class Test {

    public static void main(String[] args) {

/*        IPerson zhangsan = new ZhangSan();
        System.out.println("原始对象："+zhangsan.getClass().getName());

        DynamichouseAgent agent = new DynamichouseAgent(zhangsan);

        IPerson zhangsan1 = (IPerson)Proxy.newProxyInstance(zhangsan.getClass().getClassLoader(), zhangsan.getClass().getInterfaces(), agent);
        System.out.println("代理对象："+zhangsan1.getClass().getName());


        System.out.println(zhangsan == zhangsan1);

        zhangsan1.findHouse();*/

        DynamichouseAgent dynamichouseAgent = new DynamichouseAgent();
        IPerson iPerson = dynamichouseAgent.getInstance(new ZhangSan());
        System.out.println(iPerson.getClass().getName());
        iPerson.findHouse();
    }


}
