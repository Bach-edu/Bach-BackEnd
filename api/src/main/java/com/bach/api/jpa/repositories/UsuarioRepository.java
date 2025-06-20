package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String subject);
    Usuario getReferenceByEmail(String email);
}
