package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.SalaChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaChatRepository extends JpaRepository<SalaChat, Long> {

    // Método para encontrar una sala de chat por su nombre
    SalaChat findByNombre(String nombre);
}