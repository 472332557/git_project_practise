package com.liangzc.example.design_pattern.factory.abstract_factory;

public class Test {

    public static void main(String[] args) {

        INote note = new JavaAbstractFactory().createNote();
        IVideo video = new JavaAbstractFactory().createVideo();
        ICourse course = new JavaCourse(note,video);
        JavaCourse javaCourse = (JavaCourse) course;
        javaCourse.explain();
        javaCourse.note();
        javaCourse.video();
    }
}
