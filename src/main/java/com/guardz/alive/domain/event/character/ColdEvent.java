package com.guardz.alive.domain.event.character;

import com.guardz.alive.domain.buff.Buff;
import com.guardz.alive.domain.buff.character.CharacterBuff;
import com.guardz.alive.domain.character.Character;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.enginer.Game;

import java.util.List;

/**
 * author: wyx date: 2023/10/17 description: 感冒
 */
public class ColdEvent implements CharacterEvent {
    /**
     * 温差较大，更容易感冒
     */
    private static final int COLD_TEMPERATURE_DELTA = 10;

    /**
     * 一般情况下，感冒的概率
     */
    private static final float NORMAL_RATE = 0.05f;

    /**
     * 温差较大的情况下，感冒的概率
     */
    private static final float SPECIAL_RATE = 0.1f;

    private static ColdEvent coldEvent;

    public static ColdEvent getInstance() {
        if (coldEvent == null) {
            coldEvent = new ColdEvent();
        }
        return coldEvent;
    }

    @Override
    public String getEventName() {
        return "感冒";
    }

    @Override
    public String getEventDesc() {
        return null;
    }

    @Override
    public Boolean calculate(Game game) {
        Weather weather = game.getEnvironment()
            .getWeather();
        if (weather.getLastWeather() == null) {
            return false;
        }
        // 如果上一个天气的温度和当前温度相差5度，则有一定概率触发感冒事件
        if (weather.getLastWeather() != null && Math.abs(weather.getLastWeather()
            .getTemperature() - weather.getTemperature()) > COLD_TEMPERATURE_DELTA) {
            return Math.random() <= SPECIAL_RATE;
        } else {
            return Math.random() <= NORMAL_RATE;
        }
    }

    @Override
    public List<CharacterBuff> onEvent(Game game) {
        return null;
    }
}
