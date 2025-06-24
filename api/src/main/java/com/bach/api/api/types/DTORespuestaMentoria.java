package com.bach.api.api.types;

import com.bach.api.jpa.entities.Mentoria;

import java.time.LocalDateTime;
import java.util.List;

public record DTORespuestaMentoria(
         Long id,
         DTOResumenUsuario mentor,
         Long cursoId,
         String tema,
         List<DTOResumenVideo> videos,
         LocalDateTime fecha
) {
    public DTORespuestaMentoria(Mentoria mentoria) {
        this(mentoria.getId(), new DTOResumenUsuario(mentoria.getMentor()),
                mentoria.getCurso().getId(), mentoria.getTema() ,mentoria.getVideos().stream().map(DTOResumenVideo::new).toList(),mentoria.getFecha());
    }
}
