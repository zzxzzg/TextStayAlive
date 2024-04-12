package com.guardz.alive.core.domain.action.system;

import com.guardz.alive.core.enginer.Game;

/**
 * author: wyx date: 2024/4/11 description:
 */
public class NextTurnAction implements SystemAction {
    
    @Override
    public String getCommandPayload() {
        return "NextTurn";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getActionName() {
        return "下一回合";
    }

    @Override
    public String getActionDesc() {
        return "下一回合";
    }

    @Override
    public void doAction(Game game, String data) {
        game.nextTurn();
    }
}
