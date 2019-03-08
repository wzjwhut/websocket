package com.wzjwhut.websocket.jetty;

import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
@Log4j2
public class JettyWebsocketAnnotation {
    Session session;

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {

    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        log.info("Got connect: {}", session.getClass());
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        log.info("onMessage: {}", msg);
        try {
            session.getRemote().sendString(msg);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
