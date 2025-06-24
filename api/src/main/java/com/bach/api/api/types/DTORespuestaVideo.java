package com.bach.api.api.types;


import com.bach.api.jpa.entities.Mentoria;
import com.bach.api.jpa.entities.Video;

import java.time.LocalDateTime;
import java.util.List;

public record DTORespuestaVideo(
         Long id,
         String titulo,
         String url,
         String descripcion,
         int duracion,
         List<Long> mentoriasId,
         LocalDateTime fechaDeSubida
) {
    public DTORespuestaVideo(Video video) {
        this(video.getId(), video.getTitulo(), video.getUrl(),
                video.getDescripcion(), video.getDuracion(),
                video.getMentorias().stream().map(Mentoria::getId).toList(),video.getFechaDeSubida());
    }
}
