package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Evaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    Page<Evaluacion> findByEvaluadorId(Long evaluadorId, Pageable pageable);
}
