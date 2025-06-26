package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Desafio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DesafioRepository extends JpaRepository<Desafio, Long> {
    Page<Desafio> findByMentoriaId(Long idMentoria, Pageable pageable);

    Page<Desafio> findByCursoId(Long idCurso, Pageable pageable);
}
