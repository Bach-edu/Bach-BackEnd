package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Mentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
    Page<Mentoria> findByMentorNombreRealContainingIgnoreCase(String nombre, Pageable pageable);

    Page<Mentoria> findByCursoId(Long idCurso, Pageable pageable);

    @Query("""
      SELECT um.mentoria
      FROM UsuarioMentoria um
      WHERE um.usuario.id = :usuarioId
      """)
    Page<Mentoria> findByUsuarioId(
            @Param("usuarioId") Long usuarioId,
            Pageable pageable);

    int countByCursoId(Long idCurso);
}
