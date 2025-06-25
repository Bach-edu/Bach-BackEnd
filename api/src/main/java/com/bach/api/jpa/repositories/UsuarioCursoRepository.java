package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.UsuarioCursoCompletado;
import com.bach.api.jpa.entities.UsuarioCursoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioCursoRepository extends JpaRepository<UsuarioCursoCompletado, UsuarioCursoId> {
}
