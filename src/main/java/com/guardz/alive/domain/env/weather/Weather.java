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
     * 温度
     */
    private int temperature;
    /**
     * 湿度
     */
    private int humidity;
    /**
     * 风力
     */
    private int windSpeed;
    /**
     * 降水量
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
        String str = weatherType.getDesc() + " " + temperature + "°C 湿度" + humidity + "% 风力" + windSpeed + "m/s 降水量" + precipitation + "mm";
        return str;
    }
}
