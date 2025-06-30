package com.bach.api.api.types;


import com.bach.api.jpa.entities.Desafio;
import com.bach.api.jpa.entities.Mentoria;
import com.bach.api.jpa.entities.Video;

import java.time.LocalDateTime;
import java.util.List;

public record DTORespuestaVideo(
         Long id,
         DTORespuestaUsuario uploader,
         String titulo,
         String url,
         String descripcion,
         int duracion,
         List<Long> mentoriasId,
         List<Long> desafioId,
         LocalDateTime fechaDeSubida
) {
    public DTORespuestaVideo(Video video) {
        this(video.getId(), new DTORespuestaUsuario(video.getUploader()),video.getTitulo(), video.getUrl(),
                video.getDescripcion(), video.getDuracion(),
                video.getMentorias().stream().map(Mentoria::getId).toList(),
                video.getDesafios().stream().map(Desafio::getId).toList(),video.getFechaDeSubida());
    }
}
