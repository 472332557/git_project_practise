package com.liangzc.example.design_pattern.factory.abstract_factory;

public class PythonAbstractFactory extends AbstractFactory {
    @Override
    public INote createNote() {
        super.init();
        return new PythonNote();
    }

    @Override
    public IVideo createVideo() {
        super.init();
        return new PythonVideo();
    }
}
