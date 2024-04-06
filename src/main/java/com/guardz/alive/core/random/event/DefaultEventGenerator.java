package com.guardz.alive.core.random.event;

import java.util.List;

import com.guardz.alive.core.domain.event.character.CharacterEvent;
import com.guardz.alive.core.domain.event.env.EnvEvent;
import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.random.event.character.CharacterEventDispatcher;
import com.guardz.alive.core.random.event.env.EnvEventDispatcher;

public class DefaultEventGenerator implements EventGenerator {
    private static DefaultEventGenerator defaultGameEventRandom;

    public static DefaultEventGenerator getGameEventRandom() {
        if (defaultGameEventRandom == null) {
            defaultGameEventRandom = new DefaultEventGenerator();
        }
        return defaultGameEventRandom;
    }

    private DefaultEventGenerator() {}

    @Override
    public List<EnvEvent> generateEnvEvents(Game game) {
        return EnvEventDispatcher.dispatch(game);
    }

    @Override
    public List<CharacterEvent> generateCharacterEvents(Game game) {
        return CharacterEventDispatcher.dispatch(game);
    }
}
