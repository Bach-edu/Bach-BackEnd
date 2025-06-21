package com.bach.api.domain.websocket;

import com.bach.api.jpa.entities.ChatMensaje;
import com.bach.api.jpa.entities.SalaChat;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.service.ChatService;
import com.bach.api.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Aquí agregar lógica si se necesita algo cuando se establezca la conexión
        System.out.println("Conexión WebSocket establecida");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String content = message.getPayload();

        // Ficticios para pruebas (en un caso real, esto se debe obtener dinámicamente)
        String remitenteUsername = "usuario 1";
        String destinatarioUsername = "usuario2";

        // Obtener los objetos Usuario desde la base de datos
        Usuario remitente = usuarioRepository.findByUsername(remitenteUsername);
        Usuario destinatario = usuarioRepository.findByUsername(destinatarioUsername);

        // Verificar si los usuarios existen
        if (remitente == null || destinatario == null) {
            session.sendMessage(new TextMessage("Error: Usuario no encontrado."));
            return;
        }

        // Obtener o crear la sala de chat entre los dos usuarios
        SalaChat salaChat = chatService.getOrCreateSalaChat(remitente, destinatario);

        // Crear el mensaje de chat
        ChatMensaje chatMensaje = new ChatMensaje();
        chatMensaje.setSalaChatId(salaChat);
        chatMensaje.setRemiteteId(remitente);  // Usamos el objeto Usuario
        //como sabe quien es el que recibe el mensaje?
        chatMensaje.setContenido(content);

        // Guardamos el mensaje en la base de datos
        chatService.saveChatMessage(chatMensaje);  // Llamamos al servicio para guardar el mensaje

        // Enviar la respuesta al cliente (mensaje recibido)
        session.sendMessage(new TextMessage("Mensaje recibido: " + content));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Aquí manejar los errores del WebSocket, por ejemplo, cuando la conexión se cierre inesperadamente
        System.err.println("Error en la conexión WebSocket: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // Aquí agregar lógica cuando la conexión se cierre
        System.out.println("Conexión WebSocket cerrada: " + status.getReason());
    }
}