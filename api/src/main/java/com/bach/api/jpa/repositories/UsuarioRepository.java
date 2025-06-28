package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String subject);
    Usuario getReferenceByEmail(String email);

    @Query("""
        SELECT DISTINCT u
        FROM Usuario u
          JOIN u.mentorias um
          JOIN um.mentoria m
        WHERE m.mentor.id = :usuarioId
          AND u.activo = true
        """)
    Page<Usuario> findByMentorIdAndActivoTrue(@Param("usuarioId") Long usuarioId, Pageable pageable);

    Usuario findByUsername(String username);

}
