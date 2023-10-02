package com.guardz.alive.random.env.weather;

import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.env.weather.WeatherEventDispatcher;

/**
 * 天气随机器
 * //TODO 可以搞个策略模式，使用不同的天气生成器。比如使用算法生成，或者纯随机生成，或者半随机生成
 */
public class DefaultWeatherRandom implements WeatherRandom {
    private static DefaultWeatherRandom defaultWeatherRandom;

    public static DefaultWeatherRandom getWeatherRandom() {
        if (defaultWeatherRandom == null) {
            defaultWeatherRandom = new DefaultWeatherRandom();
        }
        return defaultWeatherRandom;
    }

    private DefaultWeatherRandom() {
    }

    /**
     * 随机天气
     */
    @Override
    public Weather random(GameTime gameTime, Weather lastWeather) {
        Weather weather = new Weather();
        weather.setGameTime(gameTime);
        weather.setLastWeather(lastWeather);
        //TODO 1. 随机当前天气



        // 2. 环境事件轮询，生成随机天气事件
        weather.setWeatherEvents(WeatherEventDispatcher.dispatch(weather));

        return weather;
    }
}
