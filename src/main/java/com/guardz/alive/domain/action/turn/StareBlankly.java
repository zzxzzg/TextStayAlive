package com.guardz.alive.domain.action.turn;

import java.util.ArrayList;
import java.util.List;

/**
 * 发呆
 */
public class StareBlankly implements TurnAction {
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
    public void doAction() {
    }
}
