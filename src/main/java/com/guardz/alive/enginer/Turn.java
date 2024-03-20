package com.guardz.alive.enginer;

import java.util.ArrayList;
import java.util.List;

import com.guardz.alive.domain.event.character.CharacterEvent;
import com.guardz.alive.domain.event.env.EnvEvent;
import org.apache.commons.collections4.CollectionUtils;

import com.guardz.alive.domain.action.turn.TurnAction;
import com.guardz.alive.domain.buff.Buff;
import com.guardz.alive.domain.buff.character.CharacterBuff;
import com.guardz.alive.domain.buff.env.EnvBuff;
import com.guardz.alive.domain.event.Event;
import com.guardz.alive.random.env.buff.DefaultEnvBuffGenerator;

/**
 * 回合
 */
public class Turn {

    private Game game;

    public static Turn nextTurn(Game game) {
        Turn turn = new Turn();
        turn.game = game;
        return turn;
    }

    public void preTurn() {
        game.getGameController().printMessage("--回合开始，当前游戏时间:" + game.getEnvironment().getGameTime().toString());
        // 1. 环境相关随机处理 (生成天气，生成环境事件等)
        game.getEnvironment()
            .preTurn();

        // 2. 产生环境事件
        List<EnvEvent> envEvents = game.getGameMode().getEventGenerator().generateEnvEvents(game);
        // 3 产生环境buff
        List<EnvBuff> envBuffs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(envEvents)) {
            envEvents.forEach(event -> {
                List<EnvBuff> list = event.onEvent(game);
                if (CollectionUtils.isNotEmpty(list)) {
                    envBuffs.addAll(list);
                }
            });
        }
        // 4. 将buff添加到环境中
        game.getEnvironment().addBuff(envBuffs);

        // 5. TODO 环境Buff生效

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
    }

    public void onTurn() {
        game.getGameController()
            .printMessage("选择你想要进行的活动");
        // TODO 1. 提示玩家哪些可以输入

        // 2. 等待玩家输入
        TurnAction turnAction = game.waitForTurnAction();

        // TODO 3. 根据新动项目，产生相关效果，影响玩家环境，玩家状态
    }

    /**
     * 是否结束游戏
     * 
     * @return false 游戏结束， true 游戏继续
     */
    public boolean postTurn() {
        // TODO 1. 结算玩家状态

        // TODO 2. 环境设置下个回合
        game.getEnvironment()
            .nextTurn();

        return true;
    }
}
