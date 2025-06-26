package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.UsuarioMentoria;
import com.bach.api.jpa.entities.UsuarioMentoriaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioMentoriaRepository extends JpaRepository<UsuarioMentoria, UsuarioMentoriaId> {
    Optional<UsuarioMentoria> findByUsuarioId(Long usuarioId);

    int countByUsuarioIdAndMentoriaCursoIdAndCompletadaTrue(Long usuarioId, Long idCurso);

    boolean existsByMentoriaIdAndUsuarioIdAndCompletadaTrue(Long idMentoria, Long usuarioId);
}
