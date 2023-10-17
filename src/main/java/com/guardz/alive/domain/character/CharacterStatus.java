package com.guardz.alive.domain.character;

import com.guardz.alive.domain.character.buff.CharacterBuff;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩家状态
 * 包括属性，buff等
 */
public class CharacterStatus {
    private List<CharacterBuff> buffs = new ArrayList<>();

    public void init() {
    }

    public void preTurn() {
    }

    public List<CharacterBuff> getBuffs() {
        return buffs;
    }

    public void setBuffs(List<CharacterBuff> buffs) {
        this.buffs = buffs;
    }
}
