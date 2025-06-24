package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para encontrar un usuario por su nombre de usuario
    Usuario findByUsername(String username);

}