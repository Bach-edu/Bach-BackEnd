package com.bach.api.jpa.websocket;

import com.bach.api.jpa.entities.ChatMensaje;
import com.bach.api.jpa.entities.SalaChat;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.service.ChatService;
import com.bach.api.jpa.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // Lista segura para múltiples hilos donde almacenamos todas las sesiones WebSocket activas
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private ChatService chatService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Se ejecuta cuando un cliente se conecta al WebSocket
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session); // Añadimos la sesión a la lista de sesiones activas
        System.out.println("Conexión WebSocket establecida");
    }

    // Maneja los mensajes recibidos desde el cliente
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            // Obtenemos el mensaje recibido como texto JSON
            String payload = message.getPayload();

            // Usamos Jackson para convertir el JSON a un Map clave-valor
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> msgMap = mapper.readValue(payload, new TypeReference<>() {});

            // Extraemos los campos del mensaje
            String remitenteUsername = msgMap.get("remitente");
            String destinatarioUsername = msgMap.get("destinatario");
            String contenido = msgMap.get("contenido");

            // Validación simple por si falta algún campo
            if (remitenteUsername == null || destinatarioUsername == null || contenido == null) {
                session.sendMessage(new TextMessage("Error: Datos incompletos"));
                return;
            }

            // Buscamos los usuarios en la base de datos
            Usuario remitente = usuarioRepository.findByUsername(remitenteUsername);
            Usuario destinatario = usuarioRepository.findByUsername(destinatarioUsername);

            // Validamos que los usuarios existan
            if (remitente == null || destinatario == null) {
                session.sendMessage(new TextMessage("Error: Usuario no encontrado"));
                return;
            }

            // Obtenemos o creamos la sala de chat entre ambos usuarios
            SalaChat salaChat = chatService.getOrCreateSalaChat(remitente, destinatario);

            // Creamos un nuevo mensaje para la base de datos
            ChatMensaje chatMensaje = new ChatMensaje();
            chatMensaje.setRemiteteId(remitente);         // Quién lo envía
            chatMensaje.setSalaChatId(salaChat);           // Sala donde se envía
            chatMensaje.setContenido(contenido);           // El texto del mensaje
            chatMensaje.setFecha(LocalDateTime.now());     // Fecha actual

            // Guardamos el mensaje en la base de datos
            chatService.saveChatMessage(chatMensaje);

            // Enviamos el mensaje a todas las sesiones WebSocket activas (a todos los usuarios conectados)
            for (WebSocketSession ws : sessions) {
                ws.sendMessage(new TextMessage(remitenteUsername + ": " + contenido));
            }

        } catch (Exception e) {
            // Enviamos un error al cliente si ocurre alguna excepción
            session.sendMessage(new TextMessage("Error interno: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    // Se ejecuta si hay un error en la conexión
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.err.println("Error en la conexión WebSocket: " + exception.getMessage());
    }

    // Se ejecuta cuando se cierra la conexión del cliente
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session); // Quitamos la sesión cerrada
        System.out.println("Conexión WebSocket cerrada: " + status.getReason());
    }
}
