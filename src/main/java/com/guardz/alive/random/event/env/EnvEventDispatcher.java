package com.guardz.alive.random.event.env;

import com.guardz.alive.domain.event.env.StormEvent;
import com.guardz.alive.domain.event.env.EnvEvent;
import com.guardz.alive.enginer.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * 环境随机事件触发器
 */
public class EnvEventDispatcher {
    private static final List<EnvEvent> WEATHER_EVENTS = new ArrayList<>();

    static {
        // 所有天气类型事件，存在顺序性
        WEATHER_EVENTS.add(StormEvent.getStormEvent());
    }

    public static List<EnvEvent> dispatch(Game game) {
        List<EnvEvent> result = new ArrayList<>();
        for (EnvEvent envEvent : WEATHER_EVENTS) {
            if (envEvent.calculate(game)) {
                result.add(envEvent);
            }
        }
        // 事件互斥
        return envEventsFilter(result);
    }

    public static List<EnvEvent> envEventsFilter(List<EnvEvent> events) {
        return events.stream().findFirst().map(List::of).orElse(new ArrayList<>());
    }
}
