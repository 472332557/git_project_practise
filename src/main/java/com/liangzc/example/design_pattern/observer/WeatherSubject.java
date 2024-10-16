package com.liangzc.example.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

;

public class WeatherSubject implements IWeatherDataSubject {

    public static final List<IObserver> observerList = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;


    //注册观察者
    public void registerObserver(IObserver observer) {
        observerList.add(observer);
    }

    //取消观察者
    public void removeObserver(IObserver observer) {
        int index = observerList.indexOf(observer);
        if (index > 0) {
            observerList.remove(index);
        }
    }

    //通知观察者更新
    @Override
    public void notifyObserver() {
        for (IObserver observer : observerList) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }


}
