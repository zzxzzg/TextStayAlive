package com.guardz.alive.core.enginer;

import com.guardz.alive.core.random.env.weather.WeatherRandom;
import com.guardz.alive.core.random.env.weather.DefaultWeatherRandom;
import com.guardz.alive.core.random.event.DefaultEventGenerator;
import com.guardz.alive.core.random.event.EventGenerator;
import lombok.Data;

/**
 * 游戏模式，配置一些游戏规则
 */
@Data
public class GameMode {
    public WeatherRandom weatherRandom = DefaultWeatherRandom.getWeatherRandom();
    public EventGenerator eventGenerator = DefaultEventGenerator.getGameEventRandom();
}
