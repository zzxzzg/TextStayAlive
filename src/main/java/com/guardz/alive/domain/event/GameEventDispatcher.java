package com.guardz.alive.domain.event;

import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.domain.env.weather.event.StormEvent;
import com.guardz.alive.domain.env.weather.event.WeatherEvent;
import com.guardz.alive.enginer.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wyx
 * date: 2023/10/17
 * description:
 */
public class GameEventDispatcher {
    private static List<GameEvent> gameEventList = new ArrayList<>();

    static {
        // 所有天气类型事件，存在顺序性
        gameEventList.add(ColdEvent.getInstance());
    }

    public static List<GameEvent> dispatch(Game game) {
        List<GameEvent> result = new ArrayList<>();
        for (GameEvent gameEvent : gameEventList) {
            if (gameEvent.random(game)) {
                result.add(gameEvent);
            }
        }
        return result;
    }
}
