package com.bach.api.api.types;

import com.bach.api.jpa.enums.Instrumento;
import com.bach.api.jpa.enums.InteresMusical;
import jakarta.validation.constraints.Email;

import java.util.Set;

public record DTOActualizacionUsuario(
        String username,

        @Email
        String email,

        String password,
        String nombreReal,

        Set<InteresMusical> intereses,
        Set<Instrumento> instrumentoDominados
) {
}
