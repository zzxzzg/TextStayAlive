package com.guardz.alive.random.env.weather;

import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.env.weather.WeatherEventDispatcher;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    /**
     * 最低温度最小值
     */
    private static final double TEMPERATURE_MIN_DOWN = -20;
    /**
     * 最高温度最小值
     */
    private static final double TEMPERATURE_MIN_UP = 0;
    /**
     * 最高温度最大值
     */
    private static final double TEMPERATURE_MAX_UP = 45;
    /**
     * 最低温度最大值
     */
    private static final double TEMPERATURE_MAX_DOWN = 30;
    /**
     * 气温计算的高斯变化率
     * 值越大，变化约平缓
     */
    private static final double GAUSSIAN_RANDOM = 3;

    private final Random random;

    private DefaultWeatherRandom() {
        random = new Random(System.currentTimeMillis());
    }

    /**
     * 随机天气
     */
    @Override
    public Weather random(GameTime gameTime, Weather lastWeather) {
        Weather weather = new Weather();
        weather.setGameTime(gameTime);
        weather.setLastWeather(lastWeather);
        //1.1. 随机当前天气
        randomTemperature(gameTime, weather);
        //1.2. 随机湿度


        // 2. 环境事件轮询，生成随机天气事件
        weather.setWeatherEvents(WeatherEventDispatcher.dispatch(weather));

        return weather;
    }

    /**
     * 随机温度
     *
     * @param gameTime
     * @return
     */
    private void randomTemperature(GameTime gameTime, Weather weather) {
        if (gameTime.getTurn() == 1) {
            List<Double> temperatureRange = getTemperatureRange(gameTime);
            double result = getNextValue(temperatureRange.get(0), temperatureRange.get(1),
                    Optional.ofNullable(weather.getLastWeather()).map(Weather::getRealTemperature).orElse(null), GAUSSIAN_RANDOM);
            weather.setRealTemperature(result);
            weather.setTemperature((int) Math.round(result));
        } else if (gameTime.getTurn() ==2){
            // 中午，气温升高
            weather.setTemperature((int) Math.round(weather.getRealTemperature()) + random.nextInt(5) + 1);
        } else if (gameTime.getTurn() == 3){
            // 晚上，气温降低
            weather.setTemperature((int) Math.round(weather.getRealTemperature()) - random.nextInt(5) - 1);
        }
    }

    /**
     * 根据游戏事件获取计算温度范围
     *
     * @param gameTime
     * @return
     */
    private List<Double> getTemperatureRange(GameTime gameTime) {
        int weatherDay = gameTime.weatherDay();
        if (weatherDay <= 60) {
            double d = TEMPERATURE_MIN_DOWN + (Math.abs(TEMPERATURE_MAX_DOWN) + Math.abs(TEMPERATURE_MIN_DOWN)) / 60 * weatherDay;
            double u = TEMPERATURE_MIN_UP + (Math.abs(TEMPERATURE_MAX_UP) + Math.abs(TEMPERATURE_MIN_UP)) / 60 * weatherDay;
            return List.of(d, u);
        } else {
            double d = TEMPERATURE_MIN_DOWN + (Math.abs(TEMPERATURE_MAX_DOWN) + Math.abs(TEMPERATURE_MIN_DOWN)) / 60 * (120 - weatherDay);
            double u = TEMPERATURE_MIN_UP + (Math.abs(TEMPERATURE_MAX_UP) + Math.abs(TEMPERATURE_MIN_UP)) / 60 * (120 - weatherDay);
            return List.of(d, u);
        }
    }


    private double getNextValue(double min, double max, Double current, double gaussianRandom) {
        if (current == null) {
            current = (min + max) / 2;
        }
        if (current < min) {
            current = min;
        }
        if (current > max) {
            current = max;
        }

        double result;
        double p = (current - min) / (max - min); // 从左边取值的概率

        do {
            // 基于 p 选择左侧还是右侧的正态分布
            boolean leftDistribution = random.nextDouble() < p;

            if (leftDistribution) {
                result = generateNormalRandom(current, (current - min) / gaussianRandom);
            } else {
                result = generateNormalRandom(current, (max - current) / gaussianRandom);
            }
        } while (result < min || result > max); // 确保在范围内
        return result;
    }

    private double generateNormalRandom(double mean, double stdDev) {
        double gaussian = random.nextGaussian();
        return mean + stdDev * gaussian;
    }
}
