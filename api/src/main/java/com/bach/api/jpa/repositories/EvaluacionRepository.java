package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Evaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    Page<Evaluacion> findByEvaluadorId(Long evaluadorId, Pageable pageable);

    @Query("""
        SELECT e
        FROM Evaluacion e
        WHERE e.video.uploader.id = :usuarioId
        """)
    Page<Evaluacion> findByVideoUploaderId(@Param("usuarioId") Long usuarioId, Pageable pageable);
}
