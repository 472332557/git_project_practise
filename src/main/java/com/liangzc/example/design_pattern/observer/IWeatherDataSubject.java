package com.liangzc.example.design_pattern.observer;

public interface IWeatherDataSubject {

    void registerObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObserver();

}
