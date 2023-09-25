package com.guardz.alive.random.event;

import com.guardz.alive.domain.event.GameEvent;

import java.util.List;

public interface GameEventRandom {
    List<GameEvent> random();
}
