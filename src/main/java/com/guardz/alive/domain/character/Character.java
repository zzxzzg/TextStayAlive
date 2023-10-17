package com.guardz.alive.domain.character;

import com.guardz.alive.domain.character.buff.CharacterBuff;
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
        game.getGameController().printMessage("---角色状态初始化完成");

        characterEnv.init();
        game.getGameController().printMessage("---角色环境初始化完成");

        bag.init();
        game.getGameController().printMessage("---角色背包初始化完成");

        game.getGameController().printMessage("--角色初始化完成");
    }

    public void preTurn() {
        // 1. 玩家状态处理
        characterStatus.preTurn();
        // 2. 玩家环境处理
        characterEnv.preTurn();
        // 3. 玩家背包
        bag.preTurn();
    }

    public void addBuff(CharacterBuff characterBuff){
        characterBuff.inject(this);
    }
}
