package com.wzjwhut.websocket;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableWebSocket
@Log4j2
public class Application  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication a = new SpringApplication(Application.class);
        a.run(Application.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @ClientEndpoint
    public static class Client{
        @OnMessage
        public void onMessage(String message, Session session) throws IOException {
            log.info("[client] onMessage: {}", message);
        }

        @OnOpen
        public void onOpen(Session session) throws IOException{
            log.info("onOpen: {}", session);
            session.getBasicRemote().sendText("hello");
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


    @Bean
    public ApplicationRunner runner() {
        return args -> {
            log.info("websocket-spring run");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(new Client(), new URI("ws://127.0.0.1:80/eques/icvss/login"));
        };
    }
}
