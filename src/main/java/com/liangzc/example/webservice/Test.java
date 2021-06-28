package com.liangzc.example.webservice;

public class Test {

    public static void main(String[] args) {


       /* String address = "http://192.168.4.48:9090/webservice";

        //发布webservice
        Endpoint.publish(address, new WebServiceDemo());
        System.out.println("SUCCESS");*/


        String code = "HTSEGI123456789";
        Integer id = 11111;
        System.out.println(code.split("HTSEGI")[1]);

        System.out.println(id + code);

    }
}
