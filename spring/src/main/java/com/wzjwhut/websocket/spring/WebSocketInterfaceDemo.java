package com.wzjwhut.websocket.spring;

import lombok.extern.log4j.Log4j2;
//import org.apache.tomcat.websocket.WsSession;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

@Component
@Log4j2
@ServerEndpoint(value = "/echo2")
public class WebSocketInterfaceDemo extends javax.websocket.Endpoint
        implements Whole<String> {

    public void onClose(Session session) {
        log.info("onClose: {}", session);
    }


    public void onError(Throwable ex){
        log.info("onError: ", ex);
    }

    @Override
    public void onOpen(final Session session, EndpointConfig config) {
        log.info("onOpen: {}", session.getClass());
        log.info("ip: {}", session.getUserProperties().get("javax.websocket.endpoint.remoteAddress"));
        //WsSession s = (WsSession)session;
        //log.info("remote ip: {}", session.);
        session.addMessageHandler(new MessageHandler.Whole<String>(){
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
        session.addMessageHandler(new MessageHandler.Whole<ByteBuffer>(){
            @Override
            public void onMessage(ByteBuffer message) {
                log.info("on binary message");
            }
        });
    }

    @Override
    public void onMessage(String message) {

    }
}