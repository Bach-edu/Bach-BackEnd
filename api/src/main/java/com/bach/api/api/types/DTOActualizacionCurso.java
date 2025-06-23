package com.bach.api.api.types;

import com.bach.api.jpa.enums.Instrumento;

public record DTOActualizacionCurso(
        String titulo,
        String descripcion,
        Instrumento instrumentoBase
) {
}
