package com.guardz.alive.domain.character.buff;

import com.guardz.alive.domain.character.Character;

/**
 * author: wyx
 * date: 2023/10/17
 * description:
 */
public interface CharacterBuff {
    /**
     * 将buff注入角色，反向控制策略。
     */
    void inject(Character character);
}
