package com.bach.api.api.types;

import com.bach.api.jpa.enums.Instrumento;

public record DTOActualizacionCurso(
        String titulo,
        String descripcion,
        Instrumento instrumentoBase
) {
    public DTOActualizacionCurso {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (instrumentoBase == null) {
            throw new IllegalArgumentException("El instrumento base no puede ser nulo");
        }
    }
}