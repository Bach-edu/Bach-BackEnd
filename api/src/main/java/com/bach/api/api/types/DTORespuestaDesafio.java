package com.bach.api.api.types;

import com.bach.api.jpa.entities.Desafio;
import com.bach.api.jpa.enums.CategoriaDesafio;
import com.bach.api.jpa.enums.Dificultad;

import java.time.LocalDateTime;

public record DTORespuestaDesafio(
         Long id,
        Long cursoId,
        Long mentoriaId,
         String titulo,
         String descripcion,
        CategoriaDesafio categoria,
        Dificultad dificultad,
        LocalDateTime fechaLimite
) {
    public DTORespuestaDesafio(Desafio desafio) {
        this(desafio.getId(), desafio.getCurso().getId(),
                desafio.getMentoria().getId(), desafio.getTitulo(), desafio.getDescripcion(),
                desafio.getCategoria(), desafio.getDificultad(), desafio.getFechaLimite());
    }
}
