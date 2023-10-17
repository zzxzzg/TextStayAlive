package com.guardz.alive.random.event;

import com.guardz.alive.domain.event.GameEvent;
import com.guardz.alive.domain.event.GameEventDispatcher;
import com.guardz.alive.enginer.Game;
import com.guardz.alive.random.env.weather.DefaultWeatherRandom;

import java.util.List;

public class DefaultGameEventRandom implements GameEventRandom{
    private static DefaultGameEventRandom defaultGameEventRandom;

    public static DefaultGameEventRandom getGameEventRandom() {
        if (defaultGameEventRandom == null) {
            defaultGameEventRandom = new DefaultGameEventRandom();
        }
        return defaultGameEventRandom;
    }

    private DefaultGameEventRandom() {
    }

    @Override
    public List<GameEvent> random(Game game) {
        return GameEventDispatcher.dispatch(game);
    }
}
