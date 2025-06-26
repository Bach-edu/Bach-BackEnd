package com.bach.api.api.types;

import com.bach.api.jpa.entities.Notification;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DTONotificacion(
        Long id,
        String tipo,
        String contenido,
        Boolean leido,
        String fechaEnvio
) {
    public DTONotificacion(Notification notification) {
        this(notification.getId(),
                notification.getTipo(),
                notification.getContenido(),
                notification.getLeido(),
                formatDateTime(notification.getFechaEnvio())); 
    }

    private static String formatDateTime(LocalDateTime fechaEnvio) {
        if (fechaEnvio != null) {
            return fechaEnvio.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        return null;
    }
}
