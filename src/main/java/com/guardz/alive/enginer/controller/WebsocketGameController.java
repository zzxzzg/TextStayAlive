package com.guardz.alive.enginer.controller;

import com.guardz.alive.domain.action.turn.TurnAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class WebsocketGameController implements GameController{
    private WebSocketSession session;

    private ArrayBlockingQueue<TurnAction> turnActionQueue = new ArrayBlockingQueue<>(5);

    private AtomicBoolean isWaiting = new AtomicBoolean(false);

    public WebsocketGameController(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public void printMessage(String message) {
        TextMessage textMessage = new TextMessage(message);
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            log.error("发送消息失败, message:{}", message, e);
            throw ControllerException.of("发送消息失败");
        }
    }

    /**
     * 等待玩家输入
     */
    @Override
    public TurnAction waitForTurnAction(){
        isWaiting.set(true);
        try {
            return turnActionQueue.take();
        } catch (InterruptedException e) {
            log.error("等待玩家输入失败", e);
            throw ControllerException.of("等待玩家输入失败");
        } finally {
            isWaiting.set(false);
        }
    }

    @Override
    public void inputTurnAction(TurnAction turnAction) {
        if (isWaiting.get() && turnActionQueue.isEmpty()){
            turnActionQueue.add(turnAction);
        }
    }
}
