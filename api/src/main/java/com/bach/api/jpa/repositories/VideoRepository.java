package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByMentoriasId(Long idMetoria, Pageable pageable);

    Page<Video> findByTituloIgnoreCaseContaining(String video, Pageable pageable);

    Page<Video> findByUploaderId(Long mentorId, Pageable pageable);
}
