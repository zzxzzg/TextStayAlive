package com.guardz.alive.start;

import com.guardz.alive.enginer.Game;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameManager {
    private Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public void addGame(String id, Game game){
        gameMap.put(id, game);
    }

    public Game getGame(String id){
        return gameMap.get(id);
    }

    public void removeGame(String id){
        gameMap.remove(id);
    }
}
