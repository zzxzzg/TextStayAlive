package com.guardz.alive.domain.event.weather;

import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.event.Event;

import java.util.List;

/**
 * 天气事件
 */
public interface WeatherEvent extends Event {

    /**
     * 计算是否触发该事件
     */
    boolean calculate(Weather weather);

    /**
     * 环境事件改变当前环境
     */
    void changeEnv();

    void onEvent();
}
