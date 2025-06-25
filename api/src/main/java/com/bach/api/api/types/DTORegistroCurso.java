package com.bach.api.api.types;

import com.bach.api.jpa.enums.Instrumento;
import jakarta.validation.constraints.NotNull;

public record DTORegistroCurso(
        @NotNull
        String titulo,
        String descripcion,
        Instrumento instrumentoBase
) {
}
