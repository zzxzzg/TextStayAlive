package com.guardz.alive.domain.action.turn;

import java.util.ArrayList;
import java.util.List;

public class TurnActionDispatcher {
    private static final List<TurnAction> TURN_ACTIONS = new ArrayList<>();

    static {
        TURN_ACTIONS.add(new StareBlankly());
    }

    public static TurnAction dispatch(String payload) {
        return TURN_ACTIONS.stream().filter(turnAction -> turnAction.getCommand().equals(payload)).findFirst().orElse(null);
    }

}

