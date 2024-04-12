package com.guardz.alive.core.enginer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.guardz.alive.core.domain.action.build.BuildAction;
import com.guardz.alive.core.domain.action.system.SystemAction;
import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.domain.building.Building;
import com.guardz.alive.core.domain.character.Character;
import com.guardz.alive.core.enginer.controller.GameController;

import lombok.Data;

/**
 * 游戏调度器
 */
@Data
public class Game {
    /**
     * 系统执行中，禁止玩家操作
     */
    private static final Integer TURN_STATUS_LOCK = 0;

    /**
     * 等待玩家操作
     */
    private static final Integer TURN_STATUS_WAIT = 1;


    private GameMode gameMode;

    private Environment environment;

    private GameController gameController;

    private AtomicBoolean isGameStart = new AtomicBoolean(false);

    private AtomicInteger turnStatus = new AtomicInteger(TURN_STATUS_LOCK);

    public static Game newInstance(GameController gameController) {
        return new Game(gameController);
    }

    private Game(GameController gameController) {
        this.gameController = gameController;
        this.gameMode = new GameMode();
        gameController.bindGame(this);
        // 初始化游戏
        init();
    }

    private Character character;

    public void init() {
        environment = new Environment(this);
        character = new Character(this);

        // 初始化环境
        environment.init();
        // 初始化角色
        character.init();

        gameController.printMessage("-游戏初始化完成");
    }

    /**
     * 开始一局游戏
     */
    public void start() {
        if (isGameStart.get()) {
            return;
        }
        isGameStart.set(true);
        gameController.printMessage("-游戏开始");

        // 执行回合准备阶段
        preTurn();
        // 设置为等待玩家操作
        setGameStatusWait();
    }

    /**
     * 结束本局游戏
     */
    public void stop() {
        isGameStart.set(false);
    }

    /**
     * 开始下一个回合
     */
    public void nextTurn(){
        // 回合结算
        postTurn();
        // 执行回合准备阶段
        preTurn();
        // 设置为等待玩家操作
        setGameStatusWait();
    }

    public void doTurnAction(TurnAction action, String data) {
        // 仅在等待玩家操作状态可以操作
        if (turnStatus.get() != TURN_STATUS_WAIT) {
            return;
        }
        // 锁定状态，流程交给系统执行
        setGameStatusLock();
        // 2. 执行action
        action.tryToDoAction(this, data);
    }

    public void doSystemAction(SystemAction systemAction, String data){
        systemAction.tryToDoAction(this, data);
    }

    private void setGameStatusWait() {
        turnStatus.set(TURN_STATUS_WAIT);
        // 通知玩家
        getGameController().sendTurnStatus(TURN_STATUS_WAIT);
    }

    private void setGameStatusLock() {
        turnStatus.set(TURN_STATUS_LOCK);
        // 通知玩家
        getGameController().sendTurnStatus(TURN_STATUS_LOCK);
    }

    /**
     * 回合准备阶段
     */
    private void preTurn() {
        getGameController()
            .printMessage("--回合开始，当前游戏时间:" + getEnvironment()
                .getGameTime()
                .toString());

        // 1. 环境相关随机处理 (生成天气，生成环境事件等)
        getEnvironment()
            .preTurn();

        // 2. 角色相关随机处理 (生成天气，生成环境事件等)
        getCharacter()
            .preTurn();
    }

    /**
     * 回合结束阶段
     * @return false 游戏结束， true 游戏继续
     */
    private boolean postTurn() {
        // TODO 1. 结算玩家状态

        // TODO 2. 环境设置下个回合
        getEnvironment()
            .nextTurn();

        return true;
    }
}
