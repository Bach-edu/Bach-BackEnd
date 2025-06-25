package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Mentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
    Page<Mentoria> findByMentorNombreRealContainingIgnoreCase(String nombre, Pageable pageable);
}
