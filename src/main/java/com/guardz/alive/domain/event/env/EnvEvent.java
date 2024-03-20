package com.guardz.alive.domain.event.env;

import com.guardz.alive.domain.buff.env.EnvBuff;
import com.guardz.alive.domain.event.Event;
import com.guardz.alive.enginer.Game;

import java.util.List;

/**
 * 天气事件
 */
public interface EnvEvent extends Event {
    List<EnvBuff> onEvent(Game game);

}
