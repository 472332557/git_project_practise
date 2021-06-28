package com.liangzc.example.design_pattern.design_principle.dependency_inversion;

public class Mary {

    public Mary() {
    }



    //v1
    public void study(){
        System.out.println("Mary学习java课程！");
    }

    //v2
    public void study(Course course){
        course.explain();
    }


    private Course course;

    //v3
    public Mary(Course course) {
        this.course = course;
    }

    //v4
    public void setCourse(Course course){
        this.course = course;
    }
    public void study1(){
        course.explain();
    }


}
