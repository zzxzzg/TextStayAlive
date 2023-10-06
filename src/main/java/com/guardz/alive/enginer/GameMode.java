package com.guardz.alive.enginer;

import com.guardz.alive.random.env.weather.WeatherRandom;
import com.guardz.alive.random.env.weather.DefaultWeatherRandom;
import com.guardz.alive.random.event.DefaultGameEventRandom;
import com.guardz.alive.random.event.GameEventRandom;
import lombok.Data;

/**
 * 游戏模式，配置一些游戏规则
 */
@Data
public class GameMode {
    public WeatherRandom weatherRandom = DefaultWeatherRandom.getWeatherRandom();
    public GameEventRandom gameEventRandom = DefaultGameEventRandom.getGameEventRandom();


}
