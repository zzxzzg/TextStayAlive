package com.guardz.alive.core.domain.action;

import com.guardz.alive.core.enginer.Game;

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

     default void tryToDoAction(Game game, String data){
         if (isAvailable()){
             doAction(game, data);
         }
     }

     void doAction(Game game, String data);
}
