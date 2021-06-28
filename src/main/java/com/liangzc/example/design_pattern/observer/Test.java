package com.liangzc.example.design_pattern.observer;

public class Test {

    public static void main(String[] args) {

        IWeatherDataSubject weatherDataSubject = new WeatherSubject();
        WeatherSubject weatherSubject = (WeatherSubject) weatherDataSubject;

        IObserver observer = new CurrentConditionsObserver(weatherDataSubject);
        weatherSubject.setMeasurements(80,65,30.4f);


    }
}
