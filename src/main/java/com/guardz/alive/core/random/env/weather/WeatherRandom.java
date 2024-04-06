package com.guardz.alive.core.random.env.weather;

import com.guardz.alive.core.random.Random;
import com.guardz.alive.core.domain.env.time.GameTime;
import com.guardz.alive.core.domain.env.weather.Weather;

public interface WeatherRandom extends Random {
    Weather random(GameTime gameTime, Weather lastWeather);
}
