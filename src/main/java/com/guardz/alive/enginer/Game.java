package com.guardz.alive.enginer;

import com.guardz.alive.domain.action.Action;
import com.guardz.alive.domain.action.ActionParseUtil;
import com.guardz.alive.domain.action.turn.TurnAction;
import com.guardz.alive.domain.character.Character;
import com.guardz.alive.domain.env.Environment;
import com.guardz.alive.enginer.controller.GameController;
import lombok.Data;

/**
 * 游戏调度器
 */
@Data
public class Game {

    private GameMode gameMode;

    private Environment environment;

    private GameController gameController;

    public Game(GameController gameController) {
        this.gameController = gameController;
    }

    private Character character;
    public void init(){
        environment = new Environment(this);
        character = new Character(this);
        gameMode = new GameMode();

        // 初始化环境
        environment.init();
        // 初始化角色
        character.init();
    }

    public void start(){
        Turn turn = Turn.nextTurn(this, character);
    }

    public void receivePayload(String payload){
        // 1. 解析payload
        Action action = ActionParseUtil.parseAction(payload);
        if (action == null){
            gameController.printMessage("无效的命令");
            return;
        }
        // 2. 如果是TurnAction，则写入队列
        if (action instanceof TurnAction) {
            gameController.inputTurnAction((TurnAction) action);
        }
    }

    public TurnAction waitForTurnAction(){
        TurnAction turnAction = gameController.waitForTurnAction();
        if (isTurnActionValid(turnAction)){
            return turnAction;
        }
        return waitForTurnAction();
    }

    private boolean isTurnActionValid(TurnAction turnAction){
        // TODO 检验合法性
        return true;
    }


}
