package com.liangzc.example.design_pattern.observer;

/**
 * 当前状况观察者
 */
public class CurrentConditionsObserver implements IObserver, DisplayElement {

    private IWeatherDataSubject weatherDataSubject;
    private float temperature;
    private float humidity;

    public CurrentConditionsObserver(IWeatherDataSubject weatherDataSubject) {
        this.weatherDataSubject = weatherDataSubject;
        weatherDataSubject.registerObserver(this);
    }


    @Override
    public void display() {

        System.out.println("Current conditions:" + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
