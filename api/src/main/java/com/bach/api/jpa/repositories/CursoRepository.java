package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.enums.Instrumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<CursoMusical, Long> {
    Page<CursoMusical> findByInstrumentoBase(Instrumento instrumento, Pageable pageable);
}
