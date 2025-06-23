package com.bach.api.api.types;

import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.enums.Instrumento;

public record DTORespuestaCurso(
         Long id,
         String titulo,
         String descripcion,
         Instrumento instrumentoBase
) {
    public DTORespuestaCurso(CursoMusical curso) {
        this(curso.getId(), curso.getTitulo(), curso.getDescripcion(), curso.getInstrumentoBase());
    }
}
