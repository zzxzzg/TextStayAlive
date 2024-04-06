package com.guardz.alive.iogame.logic.process.domain;

import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.enginer.controller.GameController;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameProcessManager {

    private final Map<Long, Game> gameMap = new HashMap<>();

    public void start(Long userId) {
        GameController gameController = new IOGameController();
        Game game = new Game(gameController);
        gameMap.put(userId, game);
    }

    public void end(Long userId) {
        gameMap.remove(userId);
    }
}
