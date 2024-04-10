package com.guardz.alive.iogame.logic.process.domain;

import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.enginer.controller.GameController;
import com.guardz.alive.iogame.logic.process.cmd.GameProcessCmd;
import com.guardz.alive.iogame.logic.process.message.response.ProcessCommonMessage;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.commumication.BroadcastContext;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;

public class IOGameController implements GameController {
    private final Long userId;

    public IOGameController(Long userId) {
        this.userId = userId;
    }

    @Override
    public void printMessage(String message) {
        ProcessCommonMessage processCommonMessage = new ProcessCommonMessage();
        processCommonMessage.message = message;

        CmdInfo cmdInfo = CmdInfo.of(GameProcessCmd.cmd, GameProcessCmd.commonMessage);
        BroadcastContext broadcastContext = BrokerClientHelper.getBroadcastContext();
        broadcastContext.broadcast(cmdInfo, processCommonMessage, userId);
    }

    @Override
    public TurnAction waitForTurnAction() {
        return null;
    }

    @Override
    public void inputTurnAction(TurnAction turnAction) {

    }
}
