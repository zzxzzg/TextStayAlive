package com.guardz.alive.domain.action.build;

import com.guardz.alive.domain.action.Action;
import com.guardz.alive.domain.building.Building;

public abstract class BuildAction<T extends Building> implements Action {
    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public String getActionName() {
        return null;
    }

    @Override
    public String getActionDesc() {
        return null;
    }

    @Override
    public void doAction() {

    }
}
