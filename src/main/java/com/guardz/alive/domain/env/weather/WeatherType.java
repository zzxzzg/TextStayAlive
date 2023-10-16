package com.guardz.alive.domain.env.weather;

public enum WeatherType {
    SUNNY("晴天"),
    CLOUDY("多云"),
    RAINY("雨"),
    SNOWY("雪"),
    FOGGY("雾"),
    SLEET("雨夹雪"),
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