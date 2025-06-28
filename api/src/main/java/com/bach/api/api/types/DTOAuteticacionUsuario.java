package com.bach.api.api.types;

import jakarta.validation.constraints.NotNull;

public record DTOAuteticacionUsuario(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
