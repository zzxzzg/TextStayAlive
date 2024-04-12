package com.guardz.alive.iogame.logic.process.domain;

import java.util.HashMap;
import java.util.Map;

import com.guardz.alive.core.domain.action.Action;
import com.guardz.alive.core.domain.action.ActionParseUtil;
import com.guardz.alive.iogame.logic.process.message.request.ActionRequest;
import org.springframework.stereotype.Component;

import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.enginer.controller.GameController;

@Component
public class GameProcessManager {

    private final Map<Long, GameController> gameMap = new HashMap<>();

    public void start(Long userId) {
        GameController gameController = new IOGameController(userId);
        Game.newInstance(gameController);
        gameMap.put(userId, gameController);
        gameController.startGame();
    }

    public void gameAction(Long userId, ActionRequest actionRequest) {
        GameController gameController = gameMap.get(userId);
        if (gameController != null) {
            gameController.doAction(getAction(actionRequest), actionRequest.data);
        }
    }

    public void end(Long userId) {
        GameController gameController = gameMap.get(userId);
        if (gameController != null) {
            gameController.stopGame();
        }
        gameMap.remove(userId);
    }

    private Action getAction(ActionRequest actionRequest) {
        return ActionParseUtil.parseAction(actionRequest.payload);
    }
}
