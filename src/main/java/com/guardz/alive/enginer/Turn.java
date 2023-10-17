package com.guardz.alive.enginer;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.guardz.alive.domain.action.turn.TurnAction;
import com.guardz.alive.domain.character.Character;
import com.guardz.alive.domain.event.GameEvent;

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
        game.getGameController().printMessage("--回合开始，当前游戏时间:" + game.getEnvironment().getGameTime().toString());
        // 1. 环境相关随机处理 (生成天气，生成环境事件等)
        game.getEnvironment()
            .preTurn();

        // 2. 玩家相关随机处理
        game.getCharacter()
            .preTurn();

        // 3. 生成随机事件
        List<GameEvent> gameEventList = game.getGameMode()
            .getGameEventRandom()
            .random(game);

        // 4. 随机事件影响玩家
        if (!CollectionUtils.isEmpty(gameEventList)) {
            gameEventList.forEach(gameEvent -> gameEvent.onEvent(game.getCharacter()));
        }
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
