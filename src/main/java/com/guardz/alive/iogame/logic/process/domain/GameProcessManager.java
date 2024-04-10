package com.guardz.alive.iogame.logic.process.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.enginer.controller.GameController;

@Component
public class GameProcessManager {

    private final Map<Long, Game> gameMap = new HashMap<>();

    public void start(Long userId) {
        GameController gameController = new IOGameController(userId);
        Game game = new Game(gameController);
        game.init();
        game.start();
        gameMap.put(userId, game);
    }

    public void end(Long userId) {
        Game game = gameMap.get(userId);
        if (game != null) {
            game.stop();
        }
        gameMap.remove(userId);
    }
}
