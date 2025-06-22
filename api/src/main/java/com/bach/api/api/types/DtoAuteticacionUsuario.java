package com.bach.api.api.types;

import jakarta.validation.constraints.NotNull;

public record DtoAuteticacionUsuario(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
