package com.guardz.alive.random.env.weather;

import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.random.Random;

public interface WeatherRandom extends Random {
    Weather random(GameTime gameTime, Weather lastWeather);
}
