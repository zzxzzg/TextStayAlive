package com.guardz.alive.logic.game.domain.character;

import lombok.Data;
import lombok.ToString;

/**
 * 玩家环境，记录玩家当前产生的一些互动信息
 * 比如：已经放置了多少个陷阱，是否点燃了火把
 */
@Data
@ToString
public class CharacterEnv {
    public void init() {
    }

    public void preTurn() {
    }
}
