package com.guardz.alive.domain.building;

import com.guardz.alive.domain.action.building.BuildingAction;
import com.guardz.alive.domain.buff.Buff;

import java.util.List;

public interface Building {

    /**
     * 是否满足建造条件
     */
    Boolean isCanBuild();


    /**
     * 建筑物提供的操作项
     * @return
     */
    List<BuildingAction> getActions();

    /**
     * 建筑物提供的buff
     * @return
     */
    List<Buff> getBuffs();
}
