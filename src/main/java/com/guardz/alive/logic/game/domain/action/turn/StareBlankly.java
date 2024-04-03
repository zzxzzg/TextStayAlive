package com.guardz.alive.logic.game.domain.action.turn;

/**
 * 发呆
 */
public class StareBlankly implements TurnAction {
    @Override
    public String getCommandPayload() {
        return "StareBlankly";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getActionName() {
        return "发呆";
    }

    @Override
    public String getActionDesc() {
        return "研究表明发呆令人更聪明。";
    }

    @Override
    public void doAction() {}
}
