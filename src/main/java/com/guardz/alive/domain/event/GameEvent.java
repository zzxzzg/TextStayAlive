package com.guardz.alive.domain.event;

import com.guardz.alive.domain.character.Character;

/**
 * 游戏中的随机事件
 */
public interface GameEvent {
    /**
     * 随机事件影响玩家
     */
    void onEvent(Character character);
}
