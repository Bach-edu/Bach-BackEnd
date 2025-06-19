package com.bach.api.domain.model.usuario;

import com.bach.api.domain.enums.Instrumento;
import com.bach.api.domain.enums.InteresMusical;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DtoRegistroUsuario(

        @NotNull
        String username,

        @NotNull
                @Email
        String email,

        @NotNull
        String password,

        @NotNull
        String nombreReal,

        Set<InteresMusical> intereses,
        Set<Instrumento> instrumentoDominados
) {
}
