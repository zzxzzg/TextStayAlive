package com.guardz.alive.core.domain.event.env;

import com.guardz.alive.core.domain.buff.env.EnvBuff;
import com.guardz.alive.core.enginer.Game;

import java.util.List;

public class StormEvent implements EnvEvent {
    private static StormEvent stormEvent;

    public static StormEvent getStormEvent() {
        if (stormEvent == null) {
            stormEvent = new StormEvent();
        }
        return stormEvent;
    }

    @Override
    public String getEventName() {
        return "暴风雨";
    }

    @Override
    public String getEventDesc() {
        return "发生了暴风雨";
    }

    @Override
    public Boolean calculate(Game game) {
        return false;
    }

    @Override
    public List<EnvBuff> onEvent(Game game) {
        return null;
    }
}
