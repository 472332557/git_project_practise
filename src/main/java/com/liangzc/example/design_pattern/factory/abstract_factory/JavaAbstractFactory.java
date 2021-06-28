package com.liangzc.example.design_pattern.factory.abstract_factory;

public class JavaAbstractFactory  extends AbstractFactory{

    @Override
    public INote createNote() {
        super.init();
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        super.init();
        return new JavaVideo();
    }
}
