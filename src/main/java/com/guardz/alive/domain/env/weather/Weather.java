package com.guardz.alive.domain.env.weather;

import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.event.WeatherEvent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Weather {
    /**
     * 上一天的天气，形成链表
     */
    private Weather lastWeather;

    /**
     * 该天气对象的当前时间
     */
    private GameTime gameTime;

    /**
     * 天气类型
     */
    private WeatherType weatherType;

    /**
     * 精确气温，主要用于计算下一个气温
     */
    private Double realTemperature;

    /**
     * 精确湿度，主要用于计算下一个湿度
     */
    private Double realHumidity;

    /**
     * 精确风力等级，主要用于计算下一个湿度
     */
    private Double realWindSpeed;
    /**
     * 精确下雨
     */
    private Double realPrecipitation;

    /**
     * 温度
     */
    private int temperature;

    /**
     * 湿度
     */
    private int humidity;
    /**
     * 风力
     * 0级 - 静风：小于1公里/小时
     * 1级 - 轻风：1-5公里/小时
     * 2级 - 轻微的微风：6-11公里/小时
     * 3级 - 轻柔的微风：12-19公里/小时
     * 4级 - 温和的微风：20-28公里/小时
     * 5级 - 清新的微风：29-38公里/小时
     * 6级 - 强劲的微风：39-49公里/小时
     * 7级 - 疾风：50-61公里/小时
     * 8级 - 大风：62-74公里/小时
     * 9级 - 强风：75-88公里/小时
     * 10级 - 暴风：89-102公里/小时
     * 11级 - 猛烈的暴风：103-117公里/小时
     * 12级 - 飓风：大于118公里/小时
     */
    private int windSpeed;
    /**
     * 降水量
     * 1. 轻雨：通常指的是每小时少于2.5毫米（0.098英寸）的降雨。轻雨通常不会对户外活动造成太大影响，也不会导致洪水。
     * 2. 中雨：这通常指的是每小时2.5毫米到10毫米（0.098英寸到0.39英寸）的降雨。中雨可能需要采取一些防护措施，如使用雨伞。
     * 3. 大雨：这指的是每小时10毫米到50毫米（0.39英寸到2.0英寸）的降雨，或者24小时内超过50毫米的降雨。大雨可以对交通和户外活动产生显著影响，并可能导致洪水。
     * 4. 暴雨：这是指每小时超过50毫米（2.0英寸）的降雨。暴雨可能导致洪水，造成交通中断，并可能对人员和财产构成威胁。
     * 5. 大暴雨：这是指每小时超过100毫米（4.0英寸）的降雨。大暴雨可能导致洪水，造成交通中断，并可能对人员和财产构成威胁。
     * 6. 特大暴雨：这些术语通常用于描述极端降雨事件，这些事件可能导致严重洪水和财产损失。这些术语的具体定义可能因国家和气象组织而异。
     */
    private int precipitation;

    /**
     * 天气事件
     */
    private List<WeatherEvent> weatherEvents = new ArrayList<>();

    public Weather() {
    }

    // Constructor
    public Weather(WeatherType weatherType, int temperature, int humidity, int windSpeed, int precipitation) {
        this.weatherType = weatherType;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
    }

    /**
     * 检验正确性
     */
    public boolean validateWeatherData() {
        if (temperature > 60 || temperature < -90) {
            System.out.println("Invalid temperature value.");
            return false;
        }
        if (humidity > 100 || humidity < 0) {
            System.out.println("Invalid humidity value.");
            return false;
        }
        if (windSpeed < 0) {
            System.out.println("Invalid wind speed value.");
            return false;
        }
        if (precipitation < 0) {
            System.out.println("Invalid precipitation value.");
            return false;
        }
        return true;
    }

    // Method to display weather information
    public String toString() {
        String str = "天气：" + weatherType.getDesc() + " " + temperature + "°C 湿度" + humidity + "% 风力" + windSpeed + "级 降水量" + precipitation + "级";
        return str;
    }
}
