package com.bach.api.api.types;

import com.bach.api.jpa.entities.CursoMusical;
import com.bach.api.jpa.enums.Instrumento;

import java.util.List;

public record DTORespuestaCurso(
         Long id,
         String titulo,
         String descripcion,
         Instrumento instrumentoBase,
         List<DTORespuestaMentoria> mentorias
) {
    public DTORespuestaCurso(CursoMusical curso) {
        this(curso.getId(), curso.getTitulo(), curso.getDescripcion(), curso.getInstrumentoBase(),
                curso.getMentorias().stream().map(DTORespuestaMentoria::new).toList());
    }
}
