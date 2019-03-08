package com.wzjwhut.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

//@Component
//@WebServlet(name = "MyEcho WebSocket Servlet", urlPatterns = { "/echo3" })
public class JettyWebsocketServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(10000);
        webSocketServletFactory.register(JettyWebsocketAnnotation.class);
    }
}
