package com.bach.api.api.types;

import com.bach.api.jpa.entities.Video;

public record DTOResumenVideo(
        Long id,
        String titulo,
        String url
) {
    public DTOResumenVideo(Video v) {
        this(v.getId(), v.getTitulo(), v.getUrl());
    }
}