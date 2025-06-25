/*package com.bach.api.api.types;

import com.bach.api.jpa.entities.Mentoria;
import com.bach.api.jpa.entities.Video;

import java.time.LocalDateTime;
import java.util.Set;

public record DTORespuestaMentoria(
         Long id,
         DTORespuestaUsuario mentor,
         Long cursoId,
         String tema,
         Set<Video> videos,
         LocalDateTime fecha
) {
    public DTORespuestaMentoria(Mentoria mentoria) {
        this(mentoria.getId(), new DTORespuestaUsuario(mentoria.getMentor()),
                mentoria.getCurso().getId(), mentoria.getTema() ,mentoria.getVideos(),mentoria.getFecha());
    }
}
 */