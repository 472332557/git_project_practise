package com.liangzc.example.design_pattern.design_principle.open_close;

public class Test {

    public static void main(String[] args) {
        ICourse course = new JavaCourse("Java", 8000.00);
        System.out.println("课程名称:" + course.getName() +
                "\n价格：" + course.getPrice());
        /**
         * 现在需要获取到打折以后的价格，还要展示原价，该怎么做呢？
         * 遵守开闭原则，对扩展开放，修改关闭
         * 所以，价格不能在原有的JavaCourse类中去修改，实际项目中，可能其他地方也会引用到该价格，这里修改，会导致意外的情况发生
         * 需要单独去处理
         */
        course = new JavaDiscountCourse("java", 8000.0);
        JavaDiscountCourse discountCourse = (JavaDiscountCourse) course;
        System.out.println("课程名称:" + discountCourse.getName() +
                "\n价格：" + discountCourse.getPrice() +
                "\n折扣价" + discountCourse.getDiscountPrice());
    }
}
