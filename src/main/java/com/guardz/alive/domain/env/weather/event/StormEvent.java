package com.guardz.alive.domain.env.weather.event;


import com.guardz.alive.domain.env.weather.Weather;

import java.util.List;

public class StormEvent implements WeatherEvent {
    private static StormEvent stormEvent;

    public static StormEvent getEarthQuakeEvent() {
        if (stormEvent == null) {
            stormEvent = new StormEvent();
        }
        return stormEvent;
    }

    @Override
    public boolean calculate(Weather weather, List<WeatherEvent> currentEvent) {
        return false;
    }

    @Override
    public void changeEnv() {

    }

    @Override
    public void onEvent() {

    }
}
