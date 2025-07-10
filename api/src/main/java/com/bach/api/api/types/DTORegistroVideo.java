package com.bach.api.api.types;


import jakarta.validation.constraints.NotNull;

public record DTORegistroVideo(
        @NotNull
        String titulo,
        @NotNull
        String url,
        String descripcion,
        @NotNull
        int duracion
) {
}
