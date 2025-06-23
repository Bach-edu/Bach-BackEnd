package com.bach.api.api.types;

import com.bach.api.jpa.entities.Notification;

import java.time.LocalDateTime;

public record DTONotificacion(
        Long id,
        String tipo,
        String contenido,
        Boolean leido,
        LocalDateTime fechaEnvio
) {
    public DTONotificacion(Notification notification) {
        this(notification.getId(),
                notification.getTipo(),
                notification.getContenido(),
                notification.getLeido(),
                notification.getFechaEnvio());
    }
}
