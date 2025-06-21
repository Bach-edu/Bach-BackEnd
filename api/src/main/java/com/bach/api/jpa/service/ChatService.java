package com.bach.api.jpa.service;

import com.bach.api.jpa.entities.ChatMensaje;
import com.bach.api.jpa.entities.SalaChat;
import com.bach.api.jpa.entities.Usuario;
import com.bach.api.jpa.repositories.ChatMensajeRepository;
import com.bach.api.jpa.repositories.SalaChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private SalaChatRepository salaChatRepository;

    @Autowired
    private ChatMensajeRepository chatMensajeRepository;

    // Método para obtener o crear una sala de chat entre dos usuarios
    public SalaChat getOrCreateSalaChat(Usuario remitente, Usuario destinatario) {
        // Generamos un nombre único para la sala basado en los usuarios
        String salaNombre = generateSalaNombre(remitente, destinatario);

        // Buscamos si ya existe una sala con ese nombre
        SalaChat salaChat = salaChatRepository.findByNombre(salaNombre);

        if (salaChat == null) {
            // Si no existe, la creamos
            salaChat = new SalaChat();
            salaChat.setNombre(salaNombre);
            salaChat.setTipo("1:1"); // Tipo de chat 1:1
            salaChatRepository.save(salaChat);  // Guardamos la nueva sala en la base de datos
        }

        return salaChat;
    }

    // Método para guardar un mensaje de chat
    public void saveChatMessage(ChatMensaje chatMensaje) {
        chatMensajeRepository.save(chatMensaje);  // Guardamos el mensaje en la base de datos
    }

    // Método para generar un nombre de sala único
    private String generateSalaNombre(Usuario remitente, Usuario destinatario) {
        // Generamos un nombre basado en los nombres de usuario
        return remitente.getUsername() + " - " + destinatario.getUsername();
    }
}