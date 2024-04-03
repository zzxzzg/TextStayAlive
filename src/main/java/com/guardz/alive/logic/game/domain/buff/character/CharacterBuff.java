package com.guardz.alive.logic.game.domain.buff.character;

import com.guardz.alive.logic.game.domain.buff.Buff;
import com.guardz.alive.logic.game.domain.character.Character;

/**
 * author: wyx
 * date: 2024/3/19
 * description: 角色状态
 */
public interface CharacterBuff extends Buff {

    /**
     * buff生效
     * @param character 角色
     */
    void inject(Character character);
}
