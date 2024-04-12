package com.guardz.alive.core.enginer.controller;

import com.guardz.alive.core.domain.action.Action;
import com.guardz.alive.core.domain.action.system.SystemAction;
import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.enginer.Game;

public abstract class GameController {

    private Game game;
    private Long userId;

    public GameController(Long userId) {
        this.userId = userId;
    }

    public void bindGame(Game game){
        this.game = game;
    }

    /**
     * 开始游戏
     */
    public void startGame(){
        game.start();
    }

    /**
     * 结束游戏
     */
    public void stopGame() {
        game.stop();
    }

    /**
     * 下一回合
     */
    public void nextTurn(){
        game.nextTurn();
    }

    public void doAction(Action action, String data) {
        if (action instanceof TurnAction) {
            game.doTurnAction((TurnAction)action, data);
        } else if (action instanceof SystemAction) {
            game.doSystemAction((SystemAction)action, data);
        }
    }

    /**
     * 向玩家发送普通消息
     * @param message
     */
    public abstract void printMessage(String message);

    /**
     * 发送回合状态，可操作，不可操作
     * 0 不可操作
     * 1 可操作
     * @param status
     */
    public abstract void sendTurnStatus(Integer status);



    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
