package com.guardz.alive.domain.action;

import com.guardz.alive.domain.action.system.SystemActionDispatcher;
import com.guardz.alive.domain.action.turn.TurnActionDispatcher;

/**
 * 解析用户输入命令
 */
public class ActionParseUtil {
    /**
     * 解析命令
     * turn:{action} 代表回合行动
     */
    public static Action parseAction(String payload) {
        if (payload.startsWith("turn:")){
            return TurnActionDispatcher.dispatch(payload.replaceFirst("turn:", ""));
        }
        if (payload.startsWith("sys:")){
            return SystemActionDispatcher.dispatch(payload.replaceFirst("sys:", ""));
        }
        return null;
    }
}
