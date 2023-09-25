package com.guardz.alive.domain.env.weather.event;

import com.guardz.alive.domain.env.weather.Weather;

import java.util.List;

/**
 * 天气事件
 */
public interface WeatherEvent {

    /**
     * 计算是否触发该事件
     */
    boolean calculate(Weather weather, List<WeatherEvent> currentEvent);

    /**
     * 环境事件改变当前环境
     */
    void changeEnv();

    void onEvent();
}
