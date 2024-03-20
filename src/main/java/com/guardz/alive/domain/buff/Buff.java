package com.guardz.alive.domain.buff;

import com.guardz.alive.domain.env.time.GameTime;

/**
 * author: wyx
 * date: 2024/3/19
 * description: 状态效果
 */
public interface Buff {
    String getBuffName();

    String getBuffDesc();

    /**
     * buff开始时间,包含这个时间
     * [startTime, endTime)
     */
    GameTime getStartTime();

    /**
     * buff结束时间,不包含这个时间
     * [startTime, endTime)
     */
    GameTime getEndTime();

    default boolean isBuffEnd(GameTime currentTime) {
        // currentTime >= endTime
        return currentTime.isAfter(getEndTime()) || currentTime.isEqual(getEndTime());
    }
}
