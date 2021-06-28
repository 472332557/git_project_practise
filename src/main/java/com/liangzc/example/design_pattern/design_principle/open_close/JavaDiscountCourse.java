package com.liangzc.example.design_pattern.design_principle.open_close;

public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(String name, Double price) {
        super(name, price);
    }

    public Double getDiscountPrice(){
        return super.getPrice() * 0.6;
    }
}
