package com.guardz.alive.domain.character;

import com.guardz.alive.domain.buff.character.CharacterBuff;
import com.guardz.alive.domain.event.character.CharacterEvent;
import com.guardz.alive.enginer.Game;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    private List<CharacterBuff> characterBuffs;

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
        // 6. 产生角色事件
        List<CharacterEvent> characterEvents = game.getGameMode().getEventGenerator().generateCharacterEvents(game);
        // 7. 产生角色buff
        List<CharacterBuff> characterBuffs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(characterEvents)) {
            characterEvents.forEach(event -> {
                List<CharacterBuff> list = event.onEvent(game);
                if (CollectionUtils.isNotEmpty(list)) {
                    characterBuffs.addAll(list);
                }
            });
        }
        // 8. 将buff添加到角色中
        game.getCharacter().addBuff(characterBuffs);

        // 9. TODO 角色Buff生效


//        // 1. 玩家状态处理
//        characterStatus.preTurn();
//        // 2. 玩家环境处理
//        characterEnv.preTurn();
//        // 3. 玩家背包
//        bag.preTurn();
    }

    public void addBuff(CharacterBuff buff) {
        characterBuffs.add(buff);
    }

    public void addBuff(List<CharacterBuff> buffs) {
        characterBuffs.addAll(buffs);
    }
}
