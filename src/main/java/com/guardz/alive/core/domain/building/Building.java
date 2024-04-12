package com.guardz.alive.core.domain.building;

import com.guardz.alive.core.domain.buff.Buff;

import java.util.List;

public interface Building {

    /**
     * 是否满足建造条件
     */
    Boolean isCanBuild();

    /**
     * 建筑物提供的buff
     * @return
     */
    List<Buff> getBuffs();
}
