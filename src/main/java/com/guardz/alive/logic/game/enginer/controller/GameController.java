package com.guardz.alive.logic.game.enginer.controller;

import com.guardz.alive.logic.game.domain.action.turn.TurnAction;

public interface GameController {
    /**
     * 向玩家发送消息
     * @param message
     */
    void printMessage(String message);

    /**
     * 等待玩家输入
     */
    TurnAction waitForTurnAction();

    void inputTurnAction(TurnAction turnAction);
}
