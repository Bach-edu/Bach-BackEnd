package com.bach.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.bach.api.domain.websocket.ChatWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registro del handler para WebSocket
        registry.addHandler(new ChatWebSocketHandler(), "/chat")
                .setAllowedOrigins("*");  // Permitir conexiones desde cualquier origen (modificar según necesidad)
    }
}