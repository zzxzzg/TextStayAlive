package com.guardz.alive.random.event;

import com.guardz.alive.domain.event.game.GameEvent;
import com.guardz.alive.enginer.Game;

import java.util.List;

public interface GameEventRandom {
    List<GameEvent> random(Game game);
}
