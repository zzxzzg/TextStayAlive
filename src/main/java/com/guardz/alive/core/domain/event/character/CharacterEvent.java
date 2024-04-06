package com.guardz.alive.core.domain.event.character;

import com.guardz.alive.core.domain.buff.character.CharacterBuff;
import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.domain.event.Event;

import java.util.List;

/**
 * 游戏中的随机事件
 */
public interface CharacterEvent extends Event {

    List<CharacterBuff> onEvent(Game game);
}
