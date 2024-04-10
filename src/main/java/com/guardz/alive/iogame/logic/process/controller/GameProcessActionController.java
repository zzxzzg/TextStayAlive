package com.guardz.alive.iogame.logic.process.controller;

import com.google.common.collect.Lists;
import com.guardz.alive.iogame.logic.process.cmd.GameProcessCmd;
import com.guardz.alive.iogame.logic.process.domain.GameProcessManager;
import com.guardz.alive.iogame.logic.process.executor.ProcessStartExecutor;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.commumication.ProcessorContext;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.action.skeleton.protocol.processor.EndPointLogicServerMessage;
import com.iohao.game.action.skeleton.protocol.processor.EndPointOperationEnum;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ActionController(GameProcessCmd.cmd)
public class GameProcessActionController {

    @Resource
    private ProcessStartExecutor processStartExecutor;

    @Resource
    private GameProcessManager gameProcessManager;

    @ActionMethod(GameProcessCmd.start)
    public void start(FlowContext flowContext) {
        long userId = flowContext.getUserId();
        EndPointLogicServerMessage endPointLogicServerMessage = new EndPointLogicServerMessage()
                // 需要绑定的玩家，示例中只取了当前请求匹配的玩家
                .setUserList(Lists.newArrayList(userId))
                //添加需要绑定的逻辑服id，下面绑定了两个；
                .addLogicServerId("2-1")
                // 覆盖绑定游戏逻辑服
                .setOperation(EndPointOperationEnum.COVER_BINDING);
        ProcessorContext processorContext = BrokerClientHelper.getProcessorContext();
        processorContext.invokeOneway(endPointLogicServerMessage);

        gameProcessManager.start(userId);
    }

    @ActionMethod(GameProcessCmd.end)
    public void end(FlowContext flowContext) {
        long userId = flowContext.getUserId();
        log.info("游戏结束: {}", userId);

    }
}
