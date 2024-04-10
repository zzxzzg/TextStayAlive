package com.guardz.alive.core.enginer;

import java.util.concurrent.atomic.AtomicBoolean;

import com.guardz.alive.core.domain.action.Action;
import com.guardz.alive.core.domain.action.ActionParseUtil;
import com.guardz.alive.core.domain.action.system.StartAction;
import com.guardz.alive.core.domain.action.system.SystemAction;
import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.domain.character.Character;
import com.guardz.alive.core.enginer.controller.GameController;

import lombok.Data;

/**
 * 游戏调度器
 */
@Data
public class Game {

    private GameMode gameMode;

    private Environment environment;

    private GameController gameController;

    private AtomicBoolean isGameStart = new AtomicBoolean(false);

    public Game(GameController gameController) {
        this.gameController = gameController;
    }

    private Character character;

    public void init() {
        environment = new Environment(this);
        character = new Character(this);
        gameMode = new GameMode();

        // 初始化环境
        environment.init();
        // 初始化角色
        character.init();

        gameController.printMessage("-游戏初始化完成");
    }

    public void start() {
        if (isGameStart.get()) {
            return;
        }
        isGameStart.set(true);
        // 启动线程，开始推进游戏执行
        Thread thread = new Thread(this::invokeTurn);
        thread.start();
    }

    public void stop() {
        isGameStart.set(false);
    }

    public void receivePayload(String payload) {
        // 1. 解析payload
        Action action = ActionParseUtil.parseAction(payload);
        if (action == null) {
            gameController.printMessage("无效的命令");
            return;
        }
        // 2. 如果是TurnAction，则写入队列
        if (action instanceof TurnAction) {
            gameController.inputTurnAction((TurnAction)action);
        }
        // 2. 如果是SystemAction，则写入队列
        if (action instanceof SystemAction) {
            processSystemAction((SystemAction)action);
        }
    }

    public TurnAction waitForTurnAction() {
        TurnAction turnAction = gameController.waitForTurnAction();
        if (isTurnActionValid(turnAction)) {
            return turnAction;
        }
        return waitForTurnAction();
    }

    private void processSystemAction(SystemAction systemAction) {
        if (systemAction instanceof StartAction) {
            start();
        }
    }

    private boolean isTurnActionValid(TurnAction turnAction) {
        // TODO 检验合法性
        return true;
    }

    private void invokeTurn() {
        gameController.printMessage("-游戏开始");
        while (isGameStart.get()) {
            Turn turn = Turn.nextTurn(this);
            turn.preTurn();
            turn.onTurn();
            if (!turn.postTurn()) {
                break;
            }
        }
    }
}
