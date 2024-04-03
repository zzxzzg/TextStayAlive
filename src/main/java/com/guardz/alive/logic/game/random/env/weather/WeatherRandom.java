package com.guardz.alive.logic.game.random.env.weather;

import com.guardz.alive.logic.game.domain.env.time.GameTime;
import com.guardz.alive.logic.game.domain.env.weather.Weather;
import com.guardz.alive.logic.game.random.Random;

public interface WeatherRandom extends Random {
    Weather random(GameTime gameTime, Weather lastWeather);
}
