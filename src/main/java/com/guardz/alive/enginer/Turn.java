package com.guardz.alive.enginer;

import com.guardz.alive.domain.action.turn.TurnAction;
import com.guardz.alive.domain.character.Character;
import com.guardz.alive.domain.event.GameEvent;

import java.util.List;

/**
 * 回合
 */
public class Turn {

    private Game game;

    public static Turn nextTurn(Game game, Character character) {
        Turn turn = new Turn();
        turn.game = game;
        turn.character = character;
        return turn;
    }

    private Character character;

    public void preTurn() {
        // 1. 环境相关随机处理 (生成天气，生成环境事件等)
        game.getEnvironment().preTurn();

        // 2. 玩家相关随机处理
        game.getCharacter().preTurn();

        // 3. 生成随机事件
        List<GameEvent> gameEventList = game.getGameMode().getGameEventRandom().random();

        // 4. 随机事件影响玩家
        gameEventList.forEach(gameEvent -> gameEvent.onEvent(game.getCharacter()));

    }

    public void onTurn() {
        game.getGameController().printMessage("选择你想要进行的活动");
        // TODO 1. 提示玩家哪些可以输入

        // 2. 等待玩家输入
        TurnAction turnAction = game.waitForTurnAction();

        // TODO 3. 根据新动项目，产生相关效果，影响玩家环境，玩家状态
    }

    public void postTurn() {
        // TODO 1. 结算玩家状态

        // TODO 2. 环境设置下个回合
        game.getEnvironment().nextTurn();
    }
}
