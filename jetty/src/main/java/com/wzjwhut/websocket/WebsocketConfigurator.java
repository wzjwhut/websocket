package com.wzjwhut.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Log4j2
@Component
public class WebsocketConfigurator extends ServerEndpointConfig.Configurator{

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request,
                                HandshakeResponse response) {
        log.info("modify handshake: {}", request.getRequestURI());
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        config.getUserProperties().put("getRemoteAddr",
                httpSession.getAttribute("getRemoteAddr"));
    }
}
