package com.liangzc.example.design_pattern.design_principle.open_close;

public class JavaCourse implements ICourse {

    private String name;
    private Double price;

    public JavaCourse(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getPrice() {
        return price;
    }
}
