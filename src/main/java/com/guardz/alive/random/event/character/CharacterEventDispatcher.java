package com.guardz.alive.random.event.character;

import com.guardz.alive.domain.event.character.ColdEvent;
import com.guardz.alive.domain.event.character.CharacterEvent;
import com.guardz.alive.enginer.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wyx
 * date: 2023/10/17
 * description:
 */
public class CharacterEventDispatcher {
    private static List<CharacterEvent> GAME_EVENTS = new ArrayList<>();

    static {
        // 所有天气类型事件，存在顺序性
        GAME_EVENTS.add(ColdEvent.getInstance());
    }

    public static List<CharacterEvent> dispatch(Game game) {
        List<CharacterEvent> result = new ArrayList<>();
        for (CharacterEvent characterEvent : GAME_EVENTS) {
            if (characterEvent.calculate(game)) {
                result.add(characterEvent);
            }
        }
        // 事件互斥
        return characterEventsFilter(result);
    }

    public static List<CharacterEvent> characterEventsFilter(List<CharacterEvent> events) {
        return events.stream().findFirst().map(List::of).orElse(new ArrayList<>());
    }
}
