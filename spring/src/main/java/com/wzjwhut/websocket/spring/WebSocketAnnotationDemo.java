package com.wzjwhut.websocket.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@Log4j2
@ServerEndpoint(value = "/echo1", configurator=WebsocketConfigurator.class)
public class WebSocketAnnotationDemo {

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("onMessage: {}", message);
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("onOpen: {}, {}", session);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("onClose: {}", session);
    }

    @OnError
    public void onError(Throwable ex){
        log.info("onError: ", ex);
    }
}