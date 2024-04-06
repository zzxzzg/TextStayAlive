package com.guardz.alive.core.domain.event.env;

import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.domain.buff.env.EnvBuff;
import com.guardz.alive.core.domain.event.Event;

import java.util.List;

/**
 * 天气事件
 */
public interface EnvEvent extends Event {
    List<EnvBuff> onEvent(Game game);

}
