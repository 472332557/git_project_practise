package com.liangzc.example.design_pattern.factory.abstract_factory;

public class JavaCourse implements ICourse {
    @Override
    public void explain() {
        System.out.println("这是java课程！");
    }

    private INote iNote;
    private IVideo video;


    public JavaCourse(INote iNote, IVideo video) {
        this.iNote = iNote;
        this.video = video;
    }

    public void note(){
        iNote.edit();
    }

    public void video(){
        video.replay();
    }
}
