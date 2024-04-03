package com.guardz.alive.logic.game.random.event;

import com.guardz.alive.logic.game.domain.event.env.EnvEvent;
import com.guardz.alive.logic.game.domain.event.character.CharacterEvent;
import com.guardz.alive.logic.game.enginer.Game;

import java.util.List;

public interface EventGenerator {

    List<EnvEvent> generateEnvEvents(Game game);

    List<CharacterEvent> generateCharacterEvents(Game game);
}
