package com.guardz.alive.random.event;

import com.guardz.alive.domain.event.env.EnvEvent;
import com.guardz.alive.domain.event.character.CharacterEvent;
import com.guardz.alive.enginer.Game;

import java.util.List;

public interface EventGenerator {

    List<EnvEvent> generateEnvEvents(Game game);

    List<CharacterEvent> generateCharacterEvents(Game game);
}
