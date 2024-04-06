package com.guardz.alive.iogame.logic.process.domain;

import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.enginer.controller.GameController;

public class IOGameController implements GameController {
    @Override
    public void printMessage(String message) {

    }

    @Override
    public TurnAction waitForTurnAction() {
        return null;
    }

    @Override
    public void inputTurnAction(TurnAction turnAction) {

    }
}
