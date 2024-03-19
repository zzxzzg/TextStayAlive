package com.guardz.alive.domain.event.game;

import com.guardz.alive.domain.character.Character;
import com.guardz.alive.domain.event.Event;
import com.guardz.alive.enginer.Game;

/**
 * 游戏中的随机事件
 */
public interface GameEvent extends Event {
    /**
     * 随机事件影响玩家
     */
    void onEvent(Character character);

    /**
     * 随机事件是否发生
     * @return true 发生
     */
    boolean random(Game game);

    String getEventName();
}
