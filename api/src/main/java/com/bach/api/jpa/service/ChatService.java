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

    /**
     * Obtiene o crea una sala de chat entre dos usuarios.
     * La clave es generar un nombre único y predecible para evitar duplicados.
     */
    public SalaChat getOrCreateSalaChat(Usuario remitente, Usuario destinatario) {
        // Generamos el nombre de la sala usando ambos usernames
        String salaNombre = generateSalaNombre(remitente, destinatario);

        // Buscamos si ya existe una sala con ese nombre
        SalaChat salaChat = salaChatRepository.findByNombre(salaNombre);

        if (salaChat == null) {
            // Si no existe, creamos una nueva sala
            salaChat = new SalaChat();
            salaChat.setNombre(salaNombre);
            salaChat.setTipo("1:1"); // Tipo de chat privado entre dos usuarios
            salaChatRepository.save(salaChat);
        }

        return salaChat;
    }

    /**
     * Guarda un mensaje de chat en la base de datos.
     */
    public void saveChatMessage(ChatMensaje chatMensaje) {
        chatMensajeRepository.save(chatMensaje);
    }

    /**
     * Genera un nombre único de sala, independiente del orden de los usuarios.
     * Así evitamos duplicar salas si cambia el orden remitente-destinatario.
     */
    private String generateSalaNombre(Usuario u1, Usuario u2) {
        String username1 = u1.getUsername();
        String username2 = u2.getUsername();

        // Orden alfabético para evitar duplicados en el nombre de sala
        if (username1.compareTo(username2) < 0) {
            return username1 + "-" + username2;
        } else {
            return username2 + "-" + username1;
        }
    }
}
