package com.wzjwhut.websocket.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;

@Component
@Log4j2
@ServerEndpoint(value = "/eques/icvss/login",
        configurator=WebSocketAnnotationDemo.Configurator.class,
        subprotocols={"device", "user"})
public class WebSocketAnnotationDemo {

    public static  class Configurator extends ServerEndpointConfig.Configurator{

        @Override
        public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request,
                                    HandshakeResponse response) {
            log.info("modify handshake: {}", request.getRequestURI());

            HttpSession httpSession = (HttpSession) request.getHttpSession();
            //throw new RuntimeException("not support");
//        config.getUserProperties().put("getRemoteAddr",
//                httpSession.getAttribute("getRemoteAddr"));
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("onMessage: {}", message);
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("onOpen: {}, this: {}, {}", session.getClass(), this.hashCode(), session.getNegotiatedSubprotocol());
        log.info("uri: {}", session.getRequestURI().getPath());
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