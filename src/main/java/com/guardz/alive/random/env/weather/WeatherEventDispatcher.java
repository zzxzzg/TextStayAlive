package com.guardz.alive.random.env.weather;

import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.event.weather.StormEvent;
import com.guardz.alive.domain.event.weather.WeatherEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 环境随机事件触发器
 */
public class WeatherEventDispatcher {
    private static final List<WeatherEvent> WEATHER_EVENTS = new ArrayList<>();

    static {
        // 所有天气类型事件，存在顺序性
        WEATHER_EVENTS.add(StormEvent.getStormEvent());
    }

    public static List<WeatherEvent> dispatch(Weather weather) {
        List<WeatherEvent> result = new ArrayList<>();
        for (WeatherEvent weatherEvent : WEATHER_EVENTS) {
            if (weatherEvent.calculate(weather)) {
                result.add(weatherEvent);
            }
        }
        // 事件互斥
        return weatherEventsFilter(result);
    }

    public static List<WeatherEvent> weatherEventsFilter(List<WeatherEvent> events) {
        return events.stream().findFirst().map(List::of).orElse(new ArrayList<>());
    }
}
