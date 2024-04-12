package com.guardz.alive.core.domain.action;

import com.guardz.alive.core.domain.action.system.SystemActionDispatcher;
import com.guardz.alive.core.domain.action.turn.TurnActionDispatcher;

/**
 * 解析用户输入命令
 */
public class ActionParseUtil {
    /**
     * 解析命令
     * t:{action} 代表回合行动  (发呆，吃饭等)
     * s:{action} 代表系统行动 （查看属性，暂停等）
     */
    public static Action parseAction(String payload) {
        payload = payload.trim();
        if (payload.startsWith("t:")){
            return TurnActionDispatcher.dispatch(payload.replaceFirst("t:", ""));
        }
        if (payload.startsWith("s:")){
            return SystemActionDispatcher.dispatch(payload.replaceFirst("s:", ""));
        }
        return null;
    }
}
