package com.bach.api.api.types;

import com.bach.api.jpa.enums.CategoriaDesafio;
import com.bach.api.jpa.enums.Dificultad;

import java.time.LocalDateTime;

public record DTORegistroDesafio(
        String titulo,
        String descripcion,
        CategoriaDesafio categoria,
        Dificultad dificultad,
        LocalDateTime fechaLimite
) {
}
