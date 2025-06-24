package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.UsuarioMentoria;
import com.bach.api.jpa.entities.UsuarioMentoriaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioMentoriaRepository extends JpaRepository<UsuarioMentoria, UsuarioMentoriaId> {
}
