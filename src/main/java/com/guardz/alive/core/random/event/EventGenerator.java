package com.guardz.alive.core.random.event;

import com.guardz.alive.core.domain.event.character.CharacterEvent;
import com.guardz.alive.core.domain.event.env.EnvEvent;
import com.guardz.alive.core.enginer.Game;

import java.util.List;

public interface EventGenerator {

    List<EnvEvent> generateEnvEvents(Game game);

    List<CharacterEvent> generateCharacterEvents(Game game);
}
