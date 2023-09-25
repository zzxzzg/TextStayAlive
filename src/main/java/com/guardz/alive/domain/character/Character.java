package com.guardz.alive.domain.character;

import com.guardz.alive.enginer.Game;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Character {
    private Game game;
    /**
     * 玩家环境
     */
    private CharacterEnv characterEnv;

    /**
     * 玩家背包
     */
    private Bag bag;

    /**
     * 玩家属性
     */
    private CharacterStatus characterStatus;

    public Character(Game game) {
        this.game = game;
    }

    public void init(){
        characterStatus = new CharacterStatus();
        characterEnv = new CharacterEnv();
        bag = new Bag(this);

        characterStatus.init();
        characterEnv.init();
        bag.init();
    }

    public void preTurn() {
        // 1. 玩家状态处理
        characterStatus.preTurn();
        // 2. 玩家环境处理
        characterEnv.preTurn();
        // 3. 玩家背包
        bag.preTurn();
    }
}
