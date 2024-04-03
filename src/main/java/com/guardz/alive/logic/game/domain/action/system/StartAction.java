package com.guardz.alive.logic.game.domain.action.system;

/**
 * author: wyx date: 2023/9/26 description:
 */
public class StartAction implements SystemAction {
    @Override
    public String getCommandPayload() {
        return "start";
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public String getActionName() {
        return null;
    }

    @Override
    public String getActionDesc() {
        return null;
    }

    @Override
    public void doAction() {

    }
}
