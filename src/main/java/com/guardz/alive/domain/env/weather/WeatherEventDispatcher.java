package com.guardz.alive.domain.env.weather;

import com.guardz.alive.domain.env.weather.event.StormEvent;
import com.guardz.alive.domain.env.weather.event.WeatherEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 环境随机事件触发器
 */
public class WeatherEventDispatcher {
    private static final List<WeatherEvent> WEATHER_EVENTS = new ArrayList<>();

    static {
        // 所有天气类型事件，存在顺序性
        WEATHER_EVENTS.add(StormEvent.getEarthQuakeEvent());
    }

    public static List<WeatherEvent> dispatch(Weather weather) {
        List<WeatherEvent> result = new ArrayList<>();
        for (WeatherEvent weatherEvent : WEATHER_EVENTS) {
            if (weatherEvent.calculate(weather, result)) {
                result.add(weatherEvent);
            }
        }
        return result;
    }
}
