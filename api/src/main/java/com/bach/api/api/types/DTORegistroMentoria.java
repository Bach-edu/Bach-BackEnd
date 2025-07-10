package com.bach.api.api.types;


import jakarta.validation.constraints.NotNull;

public record DTORegistroMentoria(
        @NotNull
        String tema
) {
}
