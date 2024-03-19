package com.guardz.alive.domain.buff.character;

import com.guardz.alive.domain.buff.Buff;
import com.guardz.alive.domain.event.buff.BuffEvent;

import java.util.List;

/**
 * author: wyx
 * date: 2024/3/19
 * description: 角色状态
 */
public interface CharacterBuff extends Buff {
    String getBuffName();

    String getBuffDesc();

    /**
     * 关联的角色buff事件
     */
    List<BuffEvent> getBuffEvents();
}
