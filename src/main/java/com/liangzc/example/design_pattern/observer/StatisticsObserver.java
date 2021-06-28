package com.liangzc.example.design_pattern.observer;

/**
 * 气象统计观察者
 */
public class StatisticsObserver implements IObserver {

    private IWeatherDataSubject weatherDataSubject;

    public StatisticsObserver(IWeatherDataSubject weatherDataSubject) {
        this.weatherDataSubject = weatherDataSubject;
        weatherDataSubject.registerObserver(this);
    }


    @Override
    public void update(float temp, float humidity, float pressure) {

    }
}
