package com.guardz.alive.domain.event.character;

import com.guardz.alive.domain.buff.character.CharacterBuff;
import com.guardz.alive.domain.event.Event;
import com.guardz.alive.enginer.Game;

import java.util.List;

/**
 * 游戏中的随机事件
 */
public interface CharacterEvent extends Event {

    List<CharacterBuff> onEvent(Game game);
}
