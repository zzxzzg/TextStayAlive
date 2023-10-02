package com.guardz.alive.domain.action.system;

/**
 * author: wyx date: 2023/9/26 description:
 */
public class StartAction implements SystemAction {
    @Override
    public String getCommand() {
        return "start";
    }
}
