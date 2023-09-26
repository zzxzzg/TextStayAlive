package com.guardz.alive.domain.action.system;

import com.guardz.alive.domain.action.turn.StareBlankly;
import com.guardz.alive.domain.action.turn.TurnAction;

import java.util.ArrayList;
import java.util.List;

public class SystemActionDispatcher {
    private static final List<SystemAction> SYS_ACTIONS = new ArrayList<>();

    static {
        SYS_ACTIONS.add(new StartAction());
    }

    public static SystemAction dispatch(String payload) {
        return SYS_ACTIONS.stream().filter(turnAction -> turnAction.getCommand().equals(payload)).findFirst().orElse(null);
    }

}

