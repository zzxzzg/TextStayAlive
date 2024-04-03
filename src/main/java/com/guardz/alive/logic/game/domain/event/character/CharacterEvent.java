package com.guardz.alive.logic.game.domain.event.character;

import com.guardz.alive.logic.game.domain.buff.character.CharacterBuff;
import com.guardz.alive.logic.game.domain.event.Event;
import com.guardz.alive.logic.game.enginer.Game;

import java.util.List;

/**
 * 游戏中的随机事件
 */
public interface CharacterEvent extends Event {

    List<CharacterBuff> onEvent(Game game);
}
