package com.liangzc.example.design_pattern.observer;

/**
 * 天气预报观察者
 */
public class ForecastObserver implements IObserver {

    private IWeatherDataSubject weatherDataSubject;

    public ForecastObserver(IWeatherDataSubject weatherDataSubject) {
        this.weatherDataSubject = weatherDataSubject;
        weatherDataSubject.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {

    }
}
