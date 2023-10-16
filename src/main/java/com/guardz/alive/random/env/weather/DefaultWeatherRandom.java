package com.guardz.alive.random.env.weather;

import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.time.Season;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.env.weather.WeatherEventDispatcher;
import com.guardz.alive.domain.env.weather.WeatherType;

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


    //----------------温度相关常量-------------------
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
    //---------------------------------------------

    //----------------湿度相关常量-------------------
    /**
     * 最小湿度区间（底）
     */
    private static final int HUMIDITY_MIN_RANGER_DOWN = 5;

    /**
     * 最小湿度区间（顶）
     */
    private static final int HUMIDITY_MIN_RANGER_UP = 25;

    /**
     * 最小湿度区间（底）
     */
    private static final int HUMIDITY_MAX_RANGER_DOWN = 55;

    /**
     * 最小湿度区间（顶部）
     */
    private static final int HUMIDITY_MAX_RANGER_UP = 95;

    /**
     * 可达到最小湿度的温度
     */
    private static final int MIN_HUMIDITY_TEMPERATURE = 5;

    /**
     * 可达到最大湿度的温度
     */
    private static final int MAX_HUMIDITY_TEMPERATURE = 35;

    //---------------------------------------------

    //----------------风力相关常量-------------------
    /**
     * 正常情况下风力最小等级
     */
    private static final int MIN_WIND_SPEED = 0;
    /**
     * 正常情况下冬季风力最大等级
     */
    private static final int WINTER_MAX_WIND_SPEED = 6;

    /**
     * 正常情况下春季风力最大等级
     */
    private static final int SPRING_MAX_WIND_SPEED = 6;

    /**
     * 正常情况下夏季风力最大等级
     */
    private static final int SUMMARY_MAX_WIND_SPEED = 8;

    /**
     * 正常情况下秋季风力最大等级
     */
    private static final int AUTUMN_MAX_WIND_SPEED = 7;

    /**
     * 风力计算的高斯变化率
     * 值越大，变化约平缓
     */
    private static final double WIND_GAUSSIAN_RANDOM = 3;


    //---------------------------------------------

    //----------------降雨量相关常量-------------------
    /**
     * 正常情况下最小降雨等级
     */
    private static final int MIN_PRECIPITATION = 1;

    /**
     * 正常情况下最大降雨等级
     */
    private static final int MAX_PRECIPITATION = 5;

    /**
     * 春季降雨概率
     */
    private static final int SPRING_PRECIPITATION_PROBABILITY = 25;

    private static final double SPRING_MIDDLE_PRECIPITATION = 2;

    /**
     * 夏季降雨概率
     */
    private static final int SUMMARY_PRECIPITATION_PROBABILITY = 30;

    private static final double SUMMARY_MIDDLE_PRECIPITATION = 3;

    /**
     * 秋季降雨概率
     */
    private static final int AUTUMN_PRECIPITATION_PROBABILITY = 15;

    private static final double AUTUMN_MIDDLE_PRECIPITATION = 2.5;
    /**
     * 冬季降雨概率
     */
    private static final int WINTER_PRECIPITATION_PROBABILITY = 10;

    private static final double WINTER_MIDDLE_PRECIPITATION = 1.5;
    //---------------------------------------------

    //----------------天气类型相关常量----------------
    /**
     * 雨夹雪温度
     */
    private static final int SLEET_TEMPERATURE = 5;

    /**
     * 多云湿度
     */
    private static final int CLOUDY_HUMIDITY = 65;

    /**
     * 阴天湿度
     */
    private static final int OVERCAST_HUMIDITY = 70;

    //---------------------------------------------

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
        randomHumidity(gameTime, weather);
        //1.3. 随机风力
        randomWindSpeed(gameTime, weather);
        //1.4. 随机降雨量
        randomPrecipitation(gameTime, weather);
        //1.5. 生成天气类型
        generateWeatherType(gameTime, weather);

        // 2. 环境事件轮询，生成随机天气事件
        weather.setWeatherEvents(WeatherEventDispatcher.dispatch(weather));

        return weather;
    }

    /**
     * 生成天气类型
     *
     * @param gameTime
     * @param weather
     */
    private void generateWeatherType(GameTime gameTime, Weather weather) {
        // 如果降水量不为0，那么，如果气温低于0度就是雪天，如果气温0~SLEET_TEMPERATURE度，就是雨夹雪，如果气温大于5度，就是雨天
        if (weather.getPrecipitation() > 0) {
            if (weather.getTemperature() < 0) {
                weather.setWeatherType(WeatherType.SNOWY);
            } else if (weather.getTemperature() < SLEET_TEMPERATURE) {
                weather.setWeatherType(WeatherType.SLEET);
            } else {
                weather.setWeatherType(WeatherType.RAINY);
            }
        } else {
            // 如果降水量为0，那么，如果湿度大于65，就是多云，如果湿度大于70，就是阴天
            if (weather.getHumidity() > OVERCAST_HUMIDITY) {
                weather.setWeatherType(WeatherType.OVERCAST);
            } else if (weather.getHumidity() > CLOUDY_HUMIDITY) {
                weather.setWeatherType(WeatherType.CLOUDY);
            } else {
                weather.setWeatherType(WeatherType.SUNNY);
            }
        }
    }

    /**
     * 天气随机
     *
     * @param gameTime
     * @param weather
     */
    private void randomPrecipitation(GameTime gameTime, Weather weather) {
        // 根据当前事件和降雨概率，随机是否下雨
        int precipitationProbability;
        double middlePrecipitation;
        switch (gameTime.getSeason()) {
            case WINTER:
                precipitationProbability = WINTER_PRECIPITATION_PROBABILITY;
                middlePrecipitation = WINTER_MIDDLE_PRECIPITATION;
                break;
            case SPRING:
                precipitationProbability = SPRING_PRECIPITATION_PROBABILITY;
                middlePrecipitation = SPRING_MIDDLE_PRECIPITATION;
                break;
            case SUMMER:
                precipitationProbability = SUMMARY_PRECIPITATION_PROBABILITY;
                middlePrecipitation = SUMMARY_MIDDLE_PRECIPITATION;
                break;
            case AUTUMN:
                precipitationProbability = AUTUMN_PRECIPITATION_PROBABILITY;
                middlePrecipitation = AUTUMN_MIDDLE_PRECIPITATION;
                break;
            default:
                throw new RuntimeException("不支持的季节");
        }
        // 随机是否下雨
        boolean rain = random.nextInt(100) < precipitationProbability;
        // 如果下雨，随机使用高斯算法随机下雨等级
        if (rain) {
            double precipitation = getNextValue(MIN_PRECIPITATION, MAX_PRECIPITATION, middlePrecipitation, GAUSSIAN_RANDOM);
            weather.setRealPrecipitation(precipitation);
            weather.setPrecipitation((int) Math.round(precipitation));
        } else {
            weather.setRealPrecipitation(0d);
            weather.setPrecipitation(0);
        }
    }

    /**
     * 随机风力等级
     */
    private void randomWindSpeed(GameTime gameTime, Weather weather) {
        // 风力和季节相关，冬季风力最小，夏季风力最大
        int maxWindSpeed;
        switch (gameTime.getSeason()) {
            case WINTER:
                maxWindSpeed = WINTER_MAX_WIND_SPEED;
                break;
            case SPRING:
                maxWindSpeed = SPRING_MAX_WIND_SPEED;
                break;
            case SUMMER:
                maxWindSpeed = SUMMARY_MAX_WIND_SPEED;
                break;
            case AUTUMN:
                maxWindSpeed = AUTUMN_MAX_WIND_SPEED;
                break;
            default:
                throw new RuntimeException("不支持的季节");
        }
        // 随机风力
        double windSpeed = getNextValue(MIN_WIND_SPEED, maxWindSpeed, 2d, WIND_GAUSSIAN_RANDOM);
        weather.setRealWindSpeed(windSpeed);
        weather.setWindSpeed((int) Math.round(windSpeed));
    }

    /**
     * 随机湿度
     *
     * @param gameTime
     * @param weather
     */
    private void randomHumidity(GameTime gameTime, Weather weather) {
        // 湿度和当前温度相关，温度越高，湿度高的可能性越大。
        // 可能的最小湿度
        // 可能的最大湿度
        int minHumidity;
        int maxHumidity;
        if (weather.getTemperature() <= MIN_HUMIDITY_TEMPERATURE){
            minHumidity = HUMIDITY_MIN_RANGER_DOWN;
            maxHumidity = HUMIDITY_MAX_RANGER_DOWN;
        }else if(weather.getTemperature() >= MAX_HUMIDITY_TEMPERATURE){
            minHumidity = HUMIDITY_MIN_RANGER_UP;
            maxHumidity = HUMIDITY_MAX_RANGER_UP;
        }else{
            minHumidity = HUMIDITY_MIN_RANGER_DOWN + ((HUMIDITY_MIN_RANGER_UP - HUMIDITY_MIN_RANGER_DOWN) / (MAX_HUMIDITY_TEMPERATURE - MIN_HUMIDITY_TEMPERATURE) * (weather.getTemperature() - MIN_HUMIDITY_TEMPERATURE));
            maxHumidity = HUMIDITY_MAX_RANGER_DOWN + ((HUMIDITY_MAX_RANGER_UP - HUMIDITY_MAX_RANGER_DOWN) / (MAX_HUMIDITY_TEMPERATURE - MIN_HUMIDITY_TEMPERATURE) * (weather.getTemperature() - MIN_HUMIDITY_TEMPERATURE));
        }

        // 随机湿度
        double humidity = getNextValue(minHumidity, maxHumidity, Optional.ofNullable(weather.getLastWeather()).map(Weather::getRealHumidity).orElse(null), GAUSSIAN_RANDOM);
        weather.setRealHumidity(humidity);
        weather.setHumidity((int) Math.round(humidity));
    }

    /**
     * 随机温度
     * 根据季节决定 可能最高温度和可能最低温度，然后根据当前温度，计算正太分布后概率机的温度
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
        } else if (gameTime.getTurn() == 2) {
            // 中午，气温升高
            weather.setRealTemperature(weather.getLastWeather().getRealTemperature());
            weather.setTemperature((int) Math.round(weather.getLastWeather().getRealTemperature()) + random.nextInt(-2,5));
        } else if (gameTime.getTurn() == 3) {
            // 晚上，气温降低
            weather.setRealTemperature(weather.getLastWeather().getRealTemperature());
            weather.setTemperature((int) Math.round(weather.getLastWeather().getRealTemperature()) - random.nextInt(-2,3));
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
        // 拐点日 0-60，温度上升
        if (weatherDay <= 60) {
            double d = TEMPERATURE_MIN_DOWN + (Math.abs(TEMPERATURE_MAX_DOWN) + Math.abs(TEMPERATURE_MIN_DOWN)) / 60 * weatherDay;
            double u = TEMPERATURE_MIN_UP + (Math.abs(TEMPERATURE_MAX_UP) + Math.abs(TEMPERATURE_MIN_UP)) / 60 * weatherDay;
            return List.of(d, u);
        } else {
            // 拐点日 0-60，温度下降
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

    public static void main(String[] args) {
        DefaultWeatherRandom weatherRandom = new DefaultWeatherRandom();
        GameTime gameTime = new GameTime();
        gameTime.setSeason(Season.SPRING);
        gameTime.setDay(1);
        gameTime.setTurn(1);
        Weather lastWeather = null;
        for (int i = 0; i < 360; i++) {
            lastWeather = weatherRandom.random(gameTime, lastWeather);
            System.out.println(gameTime.toString() + " " + lastWeather.toString());
            gameTime.nextTurn();
        }
    }
}
