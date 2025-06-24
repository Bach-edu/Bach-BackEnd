package com.bach.api.api.types;


import jakarta.validation.constraints.NotNull;

public record DTORegistroVideo(
        @NotNull
        String titulo,
        String url,
        String descripcion,
        int duracion
) {
}
