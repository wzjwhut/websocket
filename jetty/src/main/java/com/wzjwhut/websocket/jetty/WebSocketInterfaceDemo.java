package com.wzjwhut.websocket.jetty;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 jetty使用这种模式时, 没有正常工作, 原因不明
 */
@Component
@Log4j2
@ServerEndpoint(value = "/echo2", configurator = WebsocketConfigurator.class)
public class WebSocketInterfaceDemo extends javax.websocket.Endpoint {

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        log.info("onClose: {}", session);
    }


    @Override
    public void onError(Session session, Throwable thr) {
        log.info("onError: ", thr);
    }

    @Override
    public void onOpen(final Session session, EndpointConfig config) {
        log.info("onOpen: {}", session.getClass());
        log.info("ip: {}", session.getUserProperties().get("javax.websocket.endpoint.remoteAddress"));
        session.addMessageHandler(new Whole<String>(){
            @Override
            public void onMessage(String message) {
                log.info("on text message: {}", message);
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        session.addMessageHandler(new Whole<ByteBuffer>(){
            @Override
            public void onMessage(ByteBuffer message) {
                log.info("on binary message");
            }
        });
    }
}