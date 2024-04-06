package com.guardz.alive.core.domain.action;

/**
 * 用户行文
 */
public interface Action {
    /**
     * 操作码
     */
    String getCommandPayload();
    /**
     * 是否可以执行该操作
     */
    boolean isAvailable();

    String getActionName();

    String getActionDesc();

    void doAction();
}
