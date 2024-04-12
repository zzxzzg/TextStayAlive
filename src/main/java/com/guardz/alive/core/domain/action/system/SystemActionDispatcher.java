package com.guardz.alive.core.domain.action.system;

import java.util.ArrayList;
import java.util.List;

public class SystemActionDispatcher {
    private static final List<SystemAction> SYS_ACTIONS = new ArrayList<>();

    static {
        SYS_ACTIONS.add(new NextTurnAction());
    }

    public static SystemAction dispatch(String payload) {
        return SYS_ACTIONS.stream().filter(systemAction -> systemAction.getCommandPayload().equals(payload)).findFirst().orElse(null);
    }

}

