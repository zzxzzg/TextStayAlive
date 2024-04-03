package com.guardz.alive.logic.game.domain.event.env;

import com.guardz.alive.logic.game.domain.buff.env.EnvBuff;
import com.guardz.alive.logic.game.domain.event.Event;
import com.guardz.alive.logic.game.enginer.Game;

import java.util.List;

/**
 * 天气事件
 */
public interface EnvEvent extends Event {
    List<EnvBuff> onEvent(Game game);

}
