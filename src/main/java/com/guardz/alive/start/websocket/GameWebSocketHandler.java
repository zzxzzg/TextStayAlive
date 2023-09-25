package com.guardz.alive.start.websocket;

import com.guardz.alive.enginer.Game;
import com.guardz.alive.enginer.controller.WebsocketGameController;
import com.guardz.alive.start.GameManager;
import org.springframework.web.socket.*;

public class GameWebSocketHandler implements WebSocketHandler {

    private final GameManager gameManager;

    public GameWebSocketHandler(GameManager gameManager){
        this.gameManager = gameManager;
    }

    /**
     * 建立连接后触发的回调
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("建立连接后触发的回调");
        TextMessage textMessage = new TextMessage("已经连入游戏" + session.getId());
        session.sendMessage(textMessage);
        // 初始化游戏
        WebsocketGameController websocketGameController = new WebsocketGameController(session);
        Game game = new Game(websocketGameController);
        game.init();
        gameManager.addGame(session.getId(), game);
    }

    /**
     * 收到消息时触发的回调
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到消息时触发的回调" + message.getPayload());
        if (message instanceof TextMessage) {
            Game game = gameManager.getGame(session.getId());
            if (game != null) {
                game.receivePayload((String) message.getPayload());
            }
        }
    }

    /**
     * 传输消息出错时触发的回调
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("传输消息出错时触发的回调");
        session.close();
    }

    /**
     * 断开连接后触发的回调
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("断开连接后触发的回调");
        gameManager.removeGame(session.getId());
    }

    /**
     * 是否处理分片消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
