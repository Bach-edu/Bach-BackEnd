package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
