package com.bach.api.api.types;

import com.bach.api.jpa.enums.CategoriaDesafio;
import com.bach.api.jpa.enums.Dificultad;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTORegistroDesafio(
        @NotNull
        String titulo,
        String descripcion,
        @NotNull
        CategoriaDesafio categoria,
        @NotNull
        Dificultad dificultad,
        @NotNull
        LocalDateTime fechaLimite
) {
}
