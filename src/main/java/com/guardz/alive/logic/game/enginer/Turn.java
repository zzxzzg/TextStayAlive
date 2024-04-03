package com.guardz.alive.logic.game.enginer;

import com.guardz.alive.logic.game.domain.action.turn.TurnAction;

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
        game.getGameController()
            .printMessage("--回合开始，当前游戏时间:" + game.getEnvironment()
                .getGameTime()
                .toString());

        // 1. 环境相关随机处理 (生成天气，生成环境事件等)
        game.getEnvironment()
            .preTurn();

        // 2. 角色相关随机处理 (生成天气，生成环境事件等)
        game.getCharacter()
            .preTurn();
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
