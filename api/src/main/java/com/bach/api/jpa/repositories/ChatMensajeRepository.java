package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.ChatMensaje;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatMensajeRepository extends JpaRepository<ChatMensaje, Long> {
}