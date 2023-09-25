package com.guardz.alive.domain.env.weather;

public enum WeatherType {
    SUNNY("晴天"),
    CLOUDY("多云"),
    RAINY("雨"),
    SNOWY("雪"),
    STORMY("暴风雨"),
    FOGGY("雾"),
    HAIL("冰雹"),
    SLEET("雨夹雪"),
    WINDY("大风"),
    THUNDERSTORM("雷暴"),
    OVERCAST("阴天")
    ;

    private final String desc;

    WeatherType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}