package com.wzjwhut.websocket;

import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.WebSocketPingPongListener;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.nio.ByteBuffer;


@Log4j2
public class JettyWebsocketInterface implements WebSocketListener, WebSocketPingPongListener{
    Session session;

    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {

    }

    @Override
    public void onWebSocketText(String s) {
        log.info("onMessage: {}", s);
        try {
            session.getRemote().sendString(s);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onWebSocketPing(ByteBuffer byteBuffer) {

    }

    @Override
    public void onWebSocketPong(ByteBuffer byteBuffer) {

    }

    @Override
    public void onWebSocketClose(int i, String s) {

    }

    @Override
    public void onWebSocketConnect(Session session) {
        log.info("Got connect: {}", session.getClass());
        this.session = session;
    }

    @Override
    public void onWebSocketError(Throwable throwable) {

    }
}
