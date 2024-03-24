package com.guardz.alive.domain.action;

/**
 * 用户行文
 */
public interface Action {
    /**
     * 是否可以执行该操作
     */
    boolean isAvailable();

    String getActionName();

    String getActionDesc();

    void doAction();
}
