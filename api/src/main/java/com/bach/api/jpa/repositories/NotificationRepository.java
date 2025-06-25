package com.bach.api.jpa.repositories;

import com.bach.api.jpa.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUsuarioIdOrderByFechaEnvioDesc(Long usuarioId);
}