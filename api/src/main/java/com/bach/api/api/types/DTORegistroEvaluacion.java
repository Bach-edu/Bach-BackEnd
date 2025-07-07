package com.bach.api.api.types;


import jakarta.validation.constraints.NotNull;

public record DTORegistroEvaluacion(
        @NotNull
        double puntuacion,
        String comentario
) {
}
