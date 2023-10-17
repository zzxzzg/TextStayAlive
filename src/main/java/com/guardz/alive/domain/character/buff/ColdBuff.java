package com.guardz.alive.domain.character.buff;

import com.guardz.alive.domain.character.Character;

/**
 * author: wyx date: 2023/10/17 description:
 */
public class ColdBuff implements CharacterBuff {

    @Override
    public void inject(Character character) {
        ColdBuff currentColdBuff = character.getCharacterStatus()
            .getBuffs()
            .stream()
            .filter(buff -> buff instanceof ColdBuff)
            .map(buff -> (ColdBuff) buff)
            .findFirst()
            .orElse(null);
        // 未感冒，添加感冒
        if (currentColdBuff == null){
            character.getCharacterStatus()
                .getBuffs()
                .add(this);
        } else {

        }
    }
}
