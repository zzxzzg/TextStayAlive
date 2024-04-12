package com.guardz.alive.iogame.logic.process.domain;

import com.guardz.alive.core.domain.action.turn.TurnAction;
import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.enginer.controller.GameController;
import com.guardz.alive.iogame.logic.process.cmd.GameProcessCmd;
import com.guardz.alive.iogame.logic.process.message.response.ProcessCommonMessage;
import com.guardz.alive.iogame.logic.process.message.response.TurnStatusMessage;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.commumication.BroadcastContext;
import com.iohao.game.action.skeleton.core.commumication.BroadcastOrderContext;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;

public class IOGameController extends GameController {

    public IOGameController(Long userId) {
        super(userId);
    }

    @Override
    public void printMessage(String message) {
        ProcessCommonMessage processCommonMessage = new ProcessCommonMessage();
        processCommonMessage.message = message;

        CmdInfo cmdInfo = CmdInfo.of(GameProcessCmd.cmd, GameProcessCmd.commonMessage);
//        BroadcastContext broadcastContext = BrokerClientHelper.getBroadcastContext();
//        broadcastContext.broadcast(cmdInfo, processCommonMessage, getUserId());
        BroadcastOrderContext broadcastOrderContext = BrokerClientHelper.getBroadcastOrderContext();
        broadcastOrderContext.broadcastOrder(cmdInfo, processCommonMessage, getUserId());
    }

    @Override
    public void sendTurnStatus(Integer status) {
        TurnStatusMessage turnStatusMessage = new TurnStatusMessage();
        turnStatusMessage.status = status;

        CmdInfo cmdInfo = CmdInfo.of(GameProcessCmd.cmd, GameProcessCmd.turnStatusMessage);
        BroadcastOrderContext broadcastOrderContext = BrokerClientHelper.getBroadcastOrderContext();
        broadcastOrderContext.broadcastOrder(cmdInfo, turnStatusMessage, getUserId());
    }
}
