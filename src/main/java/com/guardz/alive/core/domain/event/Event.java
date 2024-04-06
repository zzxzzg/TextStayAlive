package com.guardz.alive.core.domain.event;

import com.guardz.alive.core.enginer.Game;

/**
 * author: wyx
 * date: 2024/3/19
 * description: 事件
 */
public interface Event {
    String getEventName();

    String getEventDesc();

    /**
     * 判断是否触发该事件
     * @return true 触发
     */
    Boolean calculate(Game game);

}
